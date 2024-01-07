package com.discoverme.backend.project.calender;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CalenderResponse {
    private Long id;
    private Date startDate;
    private Date endDate;
}
