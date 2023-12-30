package com.discoverme.backend.project.calender;

import com.discoverme.backend.project.PeriodStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalenderResponse {
    private Long id;
    private String name;
    private PeriodStatus status;
}
