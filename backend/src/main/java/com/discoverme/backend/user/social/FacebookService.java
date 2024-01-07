package com.discoverme.backend.user.social;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FacebookService {

    private final HttpServletRequest request;

    public void postVideo(String contentUrl) throws MalformedURLException, FileNotFoundException {
        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> "".equals(cookie.getName()))
                .findFirst();
        if(cookies.isPresent()){
            String accessToken = cookies.get().getValue();
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
            User user = defaultFacebookClient.fetchObject("me", User.class);
            URL url = new URL(contentUrl);
            FileInputStream fileInputStream = new FileInputStream("");
            FacebookType response = defaultFacebookClient.publish("me/videos", FacebookType.class,
                    BinaryAttachment.with("", fileInputStream),
                    Parameter.with("message",""));
        }else{
            SecurityContextHolder.clearContext();
        }
    }
}
