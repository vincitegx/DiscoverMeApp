package com.discoverme.backend.user.social;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FacebookService {

    private final HttpServletRequest request;
    private final WebClient webClient;

    public void postVideo(String contentUrl) throws MalformedURLException, FileNotFoundException {
        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> "fb-access-token".equals(cookie.getName()))
                .findFirst();
        if(cookies.isPresent()){
            String accessToken = cookies.get().getValue();
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);

            File videoFile = new File("uploads/" + contentUrl); // Adjust path as necessary
            if (!videoFile.exists()) {
                throw new FileNotFoundException("Video file not found at specified path.");
            }

            FileInputStream fileInputStream = new FileInputStream(videoFile);
            FacebookType response = defaultFacebookClient.publish("me/videos", FacebookType.class,
                    BinaryAttachment.with(videoFile.getName(), fileInputStream),
                    Parameter.with("message", "Your message here"),
                    Parameter.with("title", "Title of testing video"),
                    Parameter.with("description", "Description of testing video"));
            System.out.println(response.getId());
            // Close file input stream after use
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//            GraphResponse publishVideoResponse = defaultFacebookClient.publish("me/videos", GraphResponse.class,
//                    BinaryAttachment.with("cat.mov", getClass().getResourceAsStream("/cat.mov")),
//                    Parameter.with("description", "Test cat"));

//            Date tomorrow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 24L);
//            GraphResponse publishMessageResponse =
//                    facebookClient.publish("me/feed", GraphResponse.class,
//                            Parameter.with("message", "RestFB test scheduled"),
//                            Parameter.with("published", false),
//                            Parameter.with("scheduled_publish_time", tomorrow));

//            InputStream thumbnailIs = getClass().getResourceAsStream("thumbnail.png");
//            InputStream videoIs = getClass().getResourceAsStream("video.mp4");
//
//            byte[] imageAsBytes = getBytesFromInputStream(thumbnailIs);
//            byte[] videoAsBytes = getBytesFromInputStream(videoIs);
//
//            DefaultFacebookClient client = new DefaultFacebookClient(getPageAccessToken(), Version.LATEST);
//            GraphResponse response = client.publish("me/videos", //
//                    GraphResponse.class, // response
//                    Arrays.asList( // array list
//                            BinaryAttachment.with("source", "test.mp4", videoAsBytes), // video
//                            BinaryAttachment.with("thumb", "thumbnail.png", imageAsBytes)), // picture
//                    Parameter.with("description", "Test video with Thumbnail"));

//            DataInputStream is =new DataInputStream(
//                    new FileInputStream(new File("C:\\Users\\samiii\\Desktop\\rock.mp4")));
//            fbClient.publish("me/videos", FacebookType.class,
//                    BinaryAttachment.with("rock.mp4", is),
//                    Parameter.with("title", "rock"),
//                    Parameter.with("description", "my first vid"));

//            DefaultFacebookClient facebookClient = new DefaultFacebookClient(accessToken, appSecret, Version.LATEST);
//            facebookClient.publish(fbPageID + "/videos", GraphResponse.class, BinaryAttachment.with("videoName.mp4", IOUtils.toByteArray(getClass().getResourceAsStream("/video/videoName.mp4"))),
//                    Parameter.with("description", " Video Description "));

        }else{
            SecurityContextHolder.clearContext();
        }
    }

    public void publishVideoToStory(){
        Object b = webClient.post()
                .uri("https://graph.facebook.com/v18.0/page_id/video_stories", uriBuilder -> uriBuilder
                        .queryParam("upload_phase", "start")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        Object c = webClient.post()
                .uri("https://rupload.facebook.com/video-upload/v18.0/video_id", uriBuilder -> uriBuilder
                        .queryParam("file_url", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        Object d = webClient.post()
                .uri("https://graph.facebook.com/v18.0/page_id/video_stories", uriBuilder -> uriBuilder
                        .queryParam("video_id", "")
                        .queryParam("upload_phase", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public void getStories(){
        Object d = webClient.get()
                .uri("https://graph.facebook.com/v18.0/page_id/stories")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
