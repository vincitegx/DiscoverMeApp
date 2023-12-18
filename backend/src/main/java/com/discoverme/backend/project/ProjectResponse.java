package com.discoverme.backend.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String songUri;
    private String songTitle;
    private String stageName;
    private Socials social;
    private String contentUri;
    private Integer noOfSupportedProjects;
    private Integer noOfVoters;
    private boolean isVoted;
    private boolean isSupported;
}
