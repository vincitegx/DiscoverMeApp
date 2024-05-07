package com.discoverme.backend.social.instagram;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.UrlDto;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.social.facebook.FBUserResponse;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.*;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.instagram.IgUser;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class InstagramController {

    @Value("${spring.security.oauth2.facebook.app-id}")
    private String appId;
    @Value("${spring.security.oauth2.facebook.app-secret}")
    private String appSecret;
    private final HttpServletResponse servletResponse;
    private DefaultFacebookClient facebookClient;
    private final UserService userService;
    private final SocialsService socialsService;
    private final UserSocialsService userSocialsService;
    @GetMapping("ig/url")
    public ResponseEntity<UrlDto> auth() {
        facebookClient = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.BUSINESS_MANAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_CONTENT_PUBLISH);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_BASIC);
        scopeBuilder.addPermission(FacebookPermissions.ADS_MANAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_READ_ENGAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_SHOW_LIST);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_CTA);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_METADATA);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_POSTS);
        String url = facebookClient.getLoginDialogUrl(appId, "https://localhost:4200/profile", scopeBuilder);
        return ResponseEntity.ok(new UrlDto(url));
    }

    @PostMapping("ig/callback")
    public ResponseEntity<FBUserResponse> callback(@RequestParam("code") String code) {
        FacebookClient.AccessToken facebookTokenResponse = facebookClient.obtainUserAccessToken(
                appId, appSecret, "https://localhost:4200/profile", code);
        FacebookClient.AccessToken facebookTokenResponseExtended = facebookClient.obtainExtendedAccessToken(
                appId, appSecret, facebookTokenResponse.getAccessToken());
        FacebookClient defaultFacebookClient = new DefaultFacebookClient(facebookTokenResponseExtended.getAccessToken(), Version.LATEST);
        Connection<Page> pageConnection = defaultFacebookClient.fetchConnection("/me/accounts/", Page.class, Parameter.with("fields","id,name,access_token,instagram_business_account"));
        List<Page> instaPageList = StreamSupport.stream(pageConnection.spliterator(), false)
                .flatMap(List::stream)
                .filter((p) -> p.getInstagramBusinessAccount() != null)
                .toList();
        Page selectedPage = instaPageList.get(0);
        FacebookClient client = new DefaultFacebookClient(selectedPage.getAccessToken(), Version.LATEST);

        String profileFields = "biography,id,ig_id,followers_count,follows_count,media_count,name,profile_picture_url,username,website";
        IgUser user =
                client.fetchObject(selectedPage.getInstagramBusinessAccount().getId(), IgUser.class,
                        Parameter.withFields(profileFields));
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM);
        Users loggedInUser = userService.getCurrentUser();
        UserSocials userSocials;
        if (userSocialsService.findUserSocial(loggedInUser, social).isPresent()) {
            userSocials = userSocialsService.findUserSocial(loggedInUser, social).get();
            userSocials.setSocialUserName(user.getUsername());
            userSocials.setSocialUserId(user.getId());
            userSocials.setAccessToken(selectedPage.getAccessToken());
        } else {
            userSocials = new UserSocials();
            userSocials.setSocialUserName(user.getUsername());
            userSocials.setSocial(socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM));
            userSocials.setUser(userService.getCurrentUser());
            userSocials.setAccessToken(selectedPage.getAccessToken());
            userSocials.setSocialUserId(user.getId());
        }
        userSocialsService.saveUserSocial(userSocials);
        return new ResponseEntity<>(new FBUserResponse(user.getUsername()), HttpStatus.OK);
    }
}
