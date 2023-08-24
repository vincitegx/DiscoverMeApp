package com.discoverme.backend.project;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String artworkUri;
    private String songUri;
    private String songTitle;
    private String stageName;
    private Set<Socials> socials;
    private Integer noOfSupportedProjects;
    private Integer noOfVoters;
    private boolean isVoted;
    private boolean isSupported;
}
