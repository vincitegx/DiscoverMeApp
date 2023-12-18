package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Calender calender;

    @Column(nullable = false, unique = true)
    private String songTitle;

    @Column(nullable = false, unique = true)
    private String songUri;

    @ManyToOne
    private Socials social;
    @Column(nullable = false)
    private String contentUri;
//    @OneToOne
//    private Content content;
    @Enumerated(EnumType.STRING)
    private ProjectApprovalStatus status;

    @Builder.Default
    private Integer voteCount = 0;

    @Builder.Default
    private Integer supportCount = 0;
}
