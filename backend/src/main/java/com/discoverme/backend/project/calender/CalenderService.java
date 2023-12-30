package com.discoverme.backend.project.calender;

import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalenderRepository calenderRepository;

    public CalenderResponse addProjectCalender(CalenderRequest calenderRequest) {
        Calender calender = new Calender();
        calender.setName(calenderRequest.getName());
        calender = calenderRepository.save(calender);
        return new CalenderResponse(calender.getId(), calender.getName(), calender.getStatus());
    }

    public void deleteProjectCalender(Long projectCalenderId) {
        calenderRepository.findById(projectCalenderId).ifPresent(calenderRepository::delete);
    }

    public CalenderResponse editProjectCalender(CalenderRequest calenderRequest) {
        Calender projectTag = calenderRepository.findByName(calenderRequest.getName()).orElseThrow(()-> new ProjectException("No such tag found"));
        projectTag.setName(projectTag.getName());
        projectTag = calenderRepository.save(projectTag);
        return new CalenderResponse(projectTag.getId(), projectTag.getName(), projectTag.getStatus());
    }
    public Calender updateProjectStatus(ProjectStatusRequest projectStatusRequest) {
        Calender calender = calenderRepository.findById(projectStatusRequest.getId()).orElseThrow(()-> new ProjectException("No such ID associated"));
        calender.setStatus(projectStatusRequest.getStatus());
        return calenderRepository.save(calender);
    }

    @Cacheable(cacheNames = "calender")
    public Calender getProjectCalender() {
        return calenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No calender found"));
    }

    public Calender findById(Long id){
        return calenderRepository.findById(id).orElseThrow(()-> new ProjectException("No such calender associated to this ID"));
    }

    public Page<CalenderResponse> getProjectCalenders(Pageable pageable) {
        Page<Calender> calenders = calenderRepository.findAll(pageable);
        List<Calender> calenderList = calenders.toList();
        Page<CalenderResponse> calenderResponses = new PageImpl(calenderList);
        calenderList.forEach(calender -> calenderResponses.and(mapCalenderToDto(calender)));
        return calenderResponses;
    }

    private CalenderResponse mapCalenderToDto(Calender calender){
        return new CalenderResponse(calender.getId(), calender.getName(), calender.getStatus());
    }
}
