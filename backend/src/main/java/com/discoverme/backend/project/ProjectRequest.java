package com.discoverme.backend.project;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectRequest {

    private String songTitle;
    private Set<PromotionPlatform> platform;
}
