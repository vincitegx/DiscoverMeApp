package com.discoverme.backend.project.calender;

import com.discoverme.backend.project.ProjectStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    @PutMapping("status")
    public ResponseEntity<Calender> updateProjectCalender(ProjectStatusRequest projectStatusRequest) {
        Calender calender = calenderService.updateProjectStatus(projectStatusRequest);
        return new ResponseEntity<>(calender, HttpStatus.OK);
    }
    @PostMapping("calender")
    public ResponseEntity<CalenderResponse> addProjectCalender(@Valid @RequestBody CalenderRequest calenderRequest) {
        CalenderResponse calenderResponse = calenderService.addProjectCalender(calenderRequest);
        return new ResponseEntity<>(calenderResponse, HttpStatus.OK);
    }

    @PutMapping("calender")
    public ResponseEntity<CalenderResponse> editProjectCalender(@Valid @RequestBody CalenderRequest calenderRequest) {
        CalenderResponse calenderResponse = calenderService.editProjectCalender(calenderRequest);
        return new ResponseEntity<>(calenderResponse, HttpStatus.OK);
    }

    @DeleteMapping("calendar")
    public ResponseEntity<Void> deleteProjectCalender(@RequestParam String id) {
        Long projectTagId = Long.parseLong(id);
        calenderService.deleteProjectCalender(projectTagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
