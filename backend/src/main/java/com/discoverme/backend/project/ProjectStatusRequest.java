package com.discoverme.backend.project;

import lombok.Data;

@Data
public class ProjectStatusRequest {
    private Long id;
    private PeriodStatus status;
}
