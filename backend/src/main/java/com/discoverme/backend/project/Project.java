package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Calender calender;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    private Socials social;

    private String contentUri;

    @Builder.Default
    private Integer supportCount = 0;

    @Builder.Default
    private Integer reactionCount = 0;
    private String videoId;
    private String postId;
    private Date publishDate;
}
