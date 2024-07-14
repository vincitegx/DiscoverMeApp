package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.user.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUserAndCalender(Users user, Calender calender);
    Optional<Project> findByUserAndCalenderAndCategory(Users user, Calender calender, String category);
//    Optional<Project> findByUserAndCalender(Users user, Calender calender);
    Page<Project> findByUser(Users user, Pageable pageable);
    List<Project> findByUser(Users user);

    @Query("SELECT p FROM Project p WHERE p.calender = :calender ORDER BY p.supportCount DESC")
    List<Project> findTop5ByCalenderOrderByVoteCountDesc(Calender calender);

    Page<Project> findByCalender(Calender calender, Pageable pageable);

    List<Project> findByCalender(Calender calender);

//    @Query(value = "SELECT p.* FROM project p INNER JOIN users u ON (p.user_id = u.id) WHERE p.calender_id = :calender AND p.status = :status AND (p.title LIKE %:search% OR u.user_name LIKE %:search%) ORDER BY p.support_count DESC", nativeQuery = true)
//    Page<Project> findByCalenderAndStatusAndTitleContainingOrUserNameContaining(Long calender,String status, String search, PageRequest request);

    @Query(value = "SELECT p.* FROM project p INNER JOIN users u ON (p.user_id = u.id) WHERE p.calender_id = :calender AND p.status = :status AND (p.title LIKE %:search% OR u.user_name LIKE %:search%)",
            countQuery = "SELECT count(p.*) FROM project p INNER JOIN users u ON (p.user_id = u.id) WHERE p.calender_id = :calender AND p.status = :status AND (p.title LIKE %:search% OR u.user_name LIKE %:search%)",
            nativeQuery = true)
    Page<Project> findByCalenderAndStatusAndTitleContainingOrUserNameContaining(Long calender, String status, String search, Pageable pageable);

    Optional<Project> findByStatus(Project project);

    @Query(value = "SELECT p.* FROM project p INNER JOIN users u ON (p.user_id = u.id) WHERE p.calender_id = :calender AND p.user_id = :user AND p.status = :status AND (p.title LIKE %:search% OR u.user_name LIKE %:search%) ORDER BY p.support_count DESC", nativeQuery = true)
    Page<Project> findByCalenderAndUserAndStatusAndTitleContainingOrUserNameContaining(Long calender, Long user,String status, String search, PageRequest request);
}