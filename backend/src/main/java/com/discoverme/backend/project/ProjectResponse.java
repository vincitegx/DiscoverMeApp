package com.discoverme.backend.project;

import com.discoverme.backend.social.Socials;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String code;
    private String title;
    private String userName;
    private Socials social;
    private String status;
    private String contentUri;
    private Integer noOfSupport;
    private boolean isSupported;
    private Integer noOfReaction;
    private boolean isReacted;
    private String publishDate;
}
