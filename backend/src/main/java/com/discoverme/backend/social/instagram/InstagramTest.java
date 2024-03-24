package com.discoverme.backend.social.instagram;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramMuteStoryRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadStoryPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramStoryItem;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class InstagramTest {

    public static void main(String[] args) {
        Instagram4j instagram = Instagram4j.builder().username("iamdavidtega").password("dondot3056").build();
        instagram.setup();
        try {
            InstagramLoginResult instagramLoginResult =  instagram.login();
            instagram.sendRequest(new InstagramUploadStoryPhotoRequest(new File(Objects.requireNonNull(InstagramTest.class.getClassLoader().getResource("img.jpg")).getFile())));
        } catch (IOException e) {
            System.out.println("failed");
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
