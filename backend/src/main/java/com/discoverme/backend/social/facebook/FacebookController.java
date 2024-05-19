package com.discoverme.backend.social.facebook;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.*;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.*;
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
    private final ApplicationProperties applicationProperties;
    private DefaultFacebookClient facebookClient;
    private final UserService userService;
    private final UserMapper userMapper;
    private final SocialsService socialsService;
    private final UserSocialsService userSocialsService;
    private final FacebookService facebookService;

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
        scopeBuilder.addPermission(FacebookPermissions.BUSINESS_MANAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_CONTENT_PUBLISH);
        scopeBuilder.addPermission(FacebookPermissions.INSTAGRAM_BASIC);
        scopeBuilder.addPermission(FacebookPermissions.ADS_MANAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_ENGAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_READ_ENGAGEMENT);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_SHOW_LIST);
        scopeBuilder.addPermission(FacebookPermissions.PAGES_MANAGE_POSTS);
        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_VIDEO);
        String url = facebookClient.getLoginDialogUrl(appId, "https://localhost:4200/profile", scopeBuilder);
        return ResponseEntity.ok(new UrlDto(url));
    }

    @PostMapping("fb/callback")
    public ResponseEntity<UserDto> callback(@RequestParam("code") String code) {
        FacebookClient.AccessToken facebookTokenResponse = facebookClient.obtainUserAccessToken(
                appId, appSecret, "https://localhost:4200/profile", code);
        FacebookClient.AccessToken facebookTokenResponseExtended = facebookClient.obtainExtendedAccessToken(
                appId, appSecret, facebookTokenResponse.getAccessToken());
        FacebookClient defaultFacebookClient = new DefaultFacebookClient(facebookTokenResponseExtended.getAccessToken(), Version.LATEST);
        Connection<Page> pageConnection = defaultFacebookClient.fetchConnection("/me/accounts", Page.class, Parameter.with("fields","id,name,access_token,username"));
        List<Page> userPages = new ArrayList<>();
        for (List<Page> pageList : pageConnection) {
            userPages.addAll(pageList);
        }
        Page selectedPage = userPages.get(0);
//        User user = defaultFacebookClient.fetchObject("me", User.class);
//        System.out.println(user);
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        Users loggedInUser = userService.getCurrentUser();
        UserSocials userSocials;
        if (userSocialsService.findUserSocial(loggedInUser, social).isPresent()) {
            userSocials = userSocialsService.findUserSocial(loggedInUser, social).get();
            userSocials.setSocialUserName(selectedPage.getUsername());
            userSocials.setSocialUserId(selectedPage.getId());
            userSocials.setAccessToken(selectedPage.getAccessToken());
        } else {
            userSocials = new UserSocials();
            userSocials.setSocialUserName(selectedPage.getUsername());
            userSocials.setSocial(socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK));
            userSocials.setUser(userService.getCurrentUser());
            userSocials.setAccessToken(selectedPage.getAccessToken());
            userSocials.setSocialUserId(selectedPage.getId());
        }
        userSocialsService.saveUserSocial(userSocials);
        loggedInUser = userService.getCurrentUser();
        UserDto user = userMapper.apply(loggedInUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("fb/disconnect")
    public ResponseEntity<UserDto> disconnectIGAccount(){
        UserDto user =  facebookService.disconnectAccount();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
