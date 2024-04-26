package com.discoverme.backend.social.facebook;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.UrlDto;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.Page;
import com.restfb.types.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class FacebookController {

    @Value("${spring.security.oauth2.facebook.app-id}")
    private String appId;
    @Value("${spring.security.oauth2.facebook.app-secret}")
    private String appSecret;
    private DefaultFacebookClient facebookClient;
    private final UserService userService;
    private final SocialsService socialsService;
    private final UserSocialsService userSocialsService;

    @GetMapping("fb/url")
    public ResponseEntity<UrlDto> auth() {
        facebookClient = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopeBuilder = new ScopeBuilder();
//        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_CONTENT_PUBLISH);
//        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
//        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_POSTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_VIDEOS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_POSTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_PHOTOS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LIKES);
//        scopeBuilder.addPermission(FacebookPermissions.USER_EVENTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_TAGGED_PLACES);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LINK);
//        scopeBuilder.addPermission(FacebookPermissions.USER_MANAGED_GROUPS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LOCATION);
//        scopeBuilder.addPermission(FacebookPermissions.USER_HOMETOWN);
//        scopeBuilder.addPermission(FacebookPermissions.USER_GENDER);
//        scopeBuilder.addPermission(FacebookPermissions.USER_FRIENDS);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_ENGAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_READ_ENGAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_SHOW_LIST);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_POSTS);
        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_VIDEO);
//        scopeBuilder.addPermission(FacebookPermissions.WHATSAPP_BUSINESS_MANAGEMENT);
        String url = facebookClient.getLoginDialogUrl(appId, "https://localhost:4200/profile", scopeBuilder);
        return ResponseEntity.ok(new UrlDto(url));
    }

    @PostMapping("fb/callback")
    public ResponseEntity<FBUserResponse> callback(@RequestParam("code") String code) {
        FacebookClient.AccessToken facebookTokenResponse = facebookClient.obtainUserAccessToken(
                appId, appSecret, "https://localhost:4200/profile", code);
        FacebookClient.AccessToken facebookTokenResponseExtended = facebookClient.obtainExtendedAccessToken(
                appId, appSecret, facebookTokenResponse.getAccessToken());
        FacebookClient defaultFacebookClient = new DefaultFacebookClient(facebookTokenResponseExtended.getAccessToken(), Version.LATEST);
        Connection<Page> pageConnection = defaultFacebookClient.fetchConnection("/me/accounts", Page.class);
        List<Page> userPages = new ArrayList<>();
        for (List<Page> pageList : pageConnection) {
            userPages.addAll(pageList);
        }
        Page selectedPage = userPages.get(0);
        String pageAccessToken = selectedPage.getAccessToken();
        System.out.println(pageAccessToken);
        User user = defaultFacebookClient.fetchObject("me", User.class);
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        Users loggedInUser = userService.getCurrentUser();
        UserSocials userSocials;
        if (userSocialsService.findUserSocial(loggedInUser, social).isPresent()) {
            userSocials = userSocialsService.findUserSocial(loggedInUser, social).get();
            userSocials.setSocialUserName(selectedPage.getName());
            userSocials.setSocialUserId(selectedPage.getId());
            userSocials.setAccessToken(pageAccessToken);
        } else {
            userSocials = new UserSocials();
            userSocials.setSocialUserName(selectedPage.getName());
            userSocials.setSocial(socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK));
            userSocials.setUser(userService.getCurrentUser());
            userSocials.setAccessToken(pageAccessToken);
            userSocials.setSocialUserId(selectedPage.getId());
        }
        userSocialsService.saveUserSocial(userSocials);
        return new ResponseEntity<>(new FBUserResponse(user.getName()), HttpStatus.OK);
    }
}
