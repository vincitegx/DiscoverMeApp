package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.project.calender.CalenderService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final CalenderService calenderService;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final FileService fileService;
    private final LoggedInUserService loggedInUserService;
    private final SocialsRepository socialsRepository;

    public ProjectResponse submitProject(ProjectRequest projectRequest, MultipartFile content){
        Calender calender = calenderService.getProjectCalender();
        Users user = userService.getCurrentUser();
        if(!calender.getStatus().equals(PeriodStatus.SUBMISSION)){
            throw new ProjectException("You cannot submit a project currently");
        }
        Optional<Project> project = projectRepository.findByUserAndCalender(user, calender);
        if(project.isPresent()){
            throw new ProjectException("You can only register one project");
        }
        String contentUri = fileService.uploadFile(content);
        Project project1 = Project.builder()
                .calender(calender)
                .songTitle(projectRequest.getSongTitle())
                .songUri(projectRequest.getSongUri())
                .user(userService.getCurrentUser())
                .contentUri(contentUri)
                .social(projectRequest.getSocial())
                .status(ProjectApprovalStatus.PENDING)
                .build();
        project1 = saveProject(project1);
        return mapProjectToResponse(project1);
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectException("No project with such ID"));
        projectRepository.delete(project);
    }

    @CachePut(cacheNames = "approved-projects")
    public ProjectResponse approveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.APPROVED);
        project = projectRepository.save(project);
        return mapProjectToResponse(project);
    }

    public ProjectResponse disApproveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.REJECTED);
        project = projectRepository.save(project);
        return mapProjectToResponse(project);
    }

    @Cacheable(cacheNames = "approved-projects")
    public Page<ProjectResponse> getApprovedProjects(PageRequest request) {
        Calender calender = calenderService.getProjectCalender();
        Page<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.APPROVED, calender, request);
        return projects.map(this::mapProjectToResponse);
    }
    
    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .songUri(project.getSongUri())
                .songTitle(project.getSongTitle())
                .contentUri(project.getContentUri())
                .isSupported(loggedInUserService.checkSupportStateForLoggedInUser(project.getId().toString()))
                .isVoted(loggedInUserService.checkVoteStateForLoggedInUser(project.getId().toString()))
                .noOfVoters(project.getVoteCount())
                .noOfSupportedProjects(loggedInUserService.getProjectsSupported(project.getUser().getId()).size())
                .social(project.getSocial())
                .stageName(project.getUser().getStageName())
                .build();
    }

    @Cacheable(cacheNames = "supporting-projects")
    public List<ProjectResponse> getTop5ProjectsWithTheHighestVoters(String id) {
        Calender calender = calenderService.getProjectCalender();
        List<Project> projects = projectRepository.findTop5ByCalenderOrderByVoteCountDesc(calender);
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        projects.forEach(project -> {
            ProjectResponse projectResponse = mapProjectToResponse(project);
            projectResponseList.add(projectResponse);
        });
        return projectResponseList;
    }

    public Page<ProjectResponse> getAllProjectsForACalender(Long id, Pageable pageable) {
        Calender calender = calenderService.findById(id);
        Page<Project> projects = projectRepository.findByCalender(calender, pageable);
        List<Project> projectList = projects.toList();
        Page<ProjectResponse> projectResponsePage = new PageImpl(projectList);
        projectList.forEach(project -> projectResponsePage.and(mapProjectToResponse(project)));
        return projectResponsePage;
    }

    public void deleteDisApprovedProjects(Pageable pageable) {
        Calender calender = calenderService.getProjectCalender();
        Page<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.REJECTED, calender, pageable);
        List<Project> projectList = projects.toList();
        projectList.forEach(project -> {
            deleteProject(project.getId());
            String contentUri = project.getContentUri();
            fileService.deleteFile(contentUri);
        });
    }

    public Page<ProjectResponse> getDisApprovedProjects(Pageable pageable) {
        Calender calender = calenderService.getProjectCalender();
        Page<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.REJECTED, calender, pageable);
        List<Project> projectList = projects.toList();
        Page<ProjectResponse> projectResponsePage = new PageImpl(projectList);
        projectList.forEach(project -> projectResponsePage.and(mapProjectToResponse(project)));
        return projectResponsePage;
    }

    public List<Socials> getAllSocials() {
        return socialsRepository.findAll();
    }
}
