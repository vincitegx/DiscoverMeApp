package com.discoverme.backend.project.calender;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projects/calenders")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    @GetMapping("active")
    public ResponseEntity<CalenderResponse> getCurrentProjectCalender() {
        Calender calender = calenderService.getProjectCalender();
        return new ResponseEntity<>(new CalenderResponse(calender.getId(), calender.getStartDate(), calender.getEndDate()), HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<CalenderResponse>> getCalenders(Pageable pageable) {
        Page<CalenderResponse> calenders = calenderService.getProjectCalenders(pageable);
        return new ResponseEntity<>(calenders, HttpStatus.OK);
    }
}
