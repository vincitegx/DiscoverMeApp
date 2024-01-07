package com.discoverme.backend.project;

import com.discoverme.backend.social.Socials;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String url;
    private String songUri;
    private String songTitle;
    private String stageName;
    private Socials social;
    private String contentUri;
    private Double percentOfSupport;
    private boolean isSupported;
}
