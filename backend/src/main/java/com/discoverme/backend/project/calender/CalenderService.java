package com.discoverme.backend.project.calender;

import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalenderRepository calenderRepository;

    public CalenderResponse addProjectCalender(CalenderRequest calenderRequest) {
        Calender calender = new Calender();
        calender.setName(calenderRequest.getName());
        calender = calenderRepository.save(calender);
        return new CalenderResponse(calender.getId(), calender.getName());
    }

    public void deleteProjectCalender(Long projectCalenderId) {
        calenderRepository.findById(projectCalenderId).ifPresent(tag->{ calenderRepository.delete(tag);});
    }

    public CalenderResponse editProjectCalender(CalenderRequest calenderRequest) {
        Calender projectTag = calenderRepository.findByName(calenderRequest.getName()).orElseThrow(()-> new ProjectException("No such tag found"));
        projectTag.setName(projectTag.getName());
        projectTag = calenderRepository.save(projectTag);
        return new CalenderResponse(projectTag.getId(), projectTag.getName());
    }
    public Calender updateProjectStatus(ProjectStatusRequest projectStatusRequest) {
        Calender calender = calenderRepository.findById(projectStatusRequest.getId()).orElseThrow(()-> new ProjectException("No such ID associated"));
        calender.setStatus(projectStatusRequest.getStatus());
        return calenderRepository.save(calender);
    }
}
