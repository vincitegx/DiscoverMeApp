package com.discoverme.backend.user.social;

import com.discoverme.backend.user.UrlDto;
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
public class FacebookController {

    @Value("${spring.security.oauth2.facebook.app-id}")
    private String appId;
    @Value("${spring.security.oauth2.facebook.app-secret}")
    private String appSecret;
    private final HttpServletResponse servletResponse;
    private DefaultFacebookClient facebookClient;
    @GetMapping("fb/url")
    public ResponseEntity<UrlDto> auth() {
        facebookClient = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
//        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_POSTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_VIDEOS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_POSTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_PHOTOS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LIKES);
//        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_VIDEO);
//        scopeBuilder.addPermission(FacebookPermissions.USER_EVENTS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_TAGGED_PLACES);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LINK);
//        scopeBuilder.addPermission(FacebookPermissions.USER_MANAGED_GROUPS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_LOCATION);
//        scopeBuilder.addPermission(FacebookPermissions.USER_HOMETOWN);
//        scopeBuilder.addPermission(FacebookPermissions.USER_GENDER);
//        scopeBuilder.addPermission(FacebookPermissions.USER_FRIENDS);
//        scopeBuilder.addPermission(FacebookPermissions.USER_BIRTHDAY);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_CONTENT_PUBLISH);
//        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_BASIC);
//        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_VIDEO);
//        scopeBuilder.addPermission(FacebookPermissions.WHATSAPP_BUSINESS_MANAGEMENT);
//        facebookClient.getFacebookEndpointUrls().
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
        Connection<Account> connection = defaultFacebookClient.fetchConnection("/me/accounts", Account.class);
        User user = defaultFacebookClient.fetchObject("me", User.class);
            Cookie refreshTokenCookie = new Cookie("fb-access-token", facebookTokenResponseExtended.getAccessToken());
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
