package com.discoverme.backend.project;

import lombok.Data;

@Data
public class ProjectRequest {

    private String songTitle;
    private String songUri;
    private Socials social;
}
