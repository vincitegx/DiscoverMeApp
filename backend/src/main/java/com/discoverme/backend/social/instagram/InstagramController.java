package com.discoverme.backend.social.instagram;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.UrlDto;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.social.FBUserResponse;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.Account;
import com.restfb.types.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_CONTENT_PUBLISH);
//        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_BASIC);
//        facebookClient.getFacebookEndpointUrls().
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
        Connection<Account> connection = defaultFacebookClient.fetchConnection("/me/accounts", Account.class);
        User user = defaultFacebookClient.fetchObject("me", User.class);
        UserSocials userSocials = new UserSocials();
        userSocials.setUserName(user.getName());
        userSocials.setSocial(socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM));
        userSocials.setUser(userService.getCurrentUser());
        userSocials.setAccessToken(facebookTokenResponseExtended.getAccessToken());
        userSocialsService.saveUserSocial(userSocials);
        Cookie refreshTokenCookie = new Cookie("ig-access-token", facebookTokenResponseExtended.getAccessToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setAttribute("SameSite", "None");
        refreshTokenCookie.setMaxAge((int) facebookTokenResponse.getExpires().getTime());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setAttribute("Priority", "High");
        servletResponse.addCookie(refreshTokenCookie);
        return new ResponseEntity<>(new FBUserResponse(user.getName()), HttpStatus.OK);
    }
}
