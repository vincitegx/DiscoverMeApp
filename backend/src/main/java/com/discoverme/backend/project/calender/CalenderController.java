package com.discoverme.backend.project.calender;

import com.discoverme.backend.project.ProjectStatusRequest;
import jakarta.validation.Valid;
import lombok.NonNull;
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

    @PutMapping("status")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Calender> updateProjectCalender(ProjectStatusRequest projectStatusRequest) {
        Calender calender = calenderService.updateProjectStatus(projectStatusRequest);
        return new ResponseEntity<>(calender, HttpStatus.OK);
    }
    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<CalenderResponse> addProjectCalender(@Valid @RequestBody CalenderRequest calenderRequest) {
        CalenderResponse calenderResponse = calenderService.addProjectCalender(calenderRequest);
        return new ResponseEntity<>(calenderResponse, HttpStatus.OK);
    }

    @PutMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<CalenderResponse> editProjectCalender(@Valid @RequestBody CalenderRequest calenderRequest) {
        CalenderResponse calenderResponse = calenderService.editProjectCalender(calenderRequest);
        return new ResponseEntity<>(calenderResponse, HttpStatus.OK);
    }

    @DeleteMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteProjectCalender(@RequestParam @NonNull String id) {
        Long projectTagId = Long.parseLong(id);
        calenderService.deleteProjectCalender(projectTagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("active")
    public ResponseEntity<CalenderResponse> getCurrentProjectCalender() {
        Calender calender = calenderService.getProjectCalender();
        return new ResponseEntity<>(new CalenderResponse(calender.getId(), calender.getName(), calender.getStatus()), HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<CalenderResponse>> getCalenders(Pageable pageable) {
        Page<CalenderResponse> calenders = calenderService.getProjectCalenders(pageable);
        return new ResponseEntity<>(calenders, HttpStatus.OK);
    }
}
