package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.user.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUserAndCalender(Users user, Calender calender);
    Page<Project> findByUser(Users user, Pageable pageable);
    Page<Project> findByStatusAndCalender(ProjectApprovalStatus status, Calender calender, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.calender = :calender ORDER BY p.voteCount DESC")
    List<Project> findTop5ByCalenderOrderByVoteCountDesc(Calender calender);

    Page<Project> findByCalender(Calender calender, Pageable pageable);
}
