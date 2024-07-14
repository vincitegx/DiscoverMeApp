package com.discoverme.backend.project;

import com.discoverme.backend.social.Socials;
import lombok.Data;

@Data
public class ProjectRequest {

    private String title;
    private Socials social;
}
