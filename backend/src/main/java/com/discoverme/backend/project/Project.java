package com.discoverme.backend.project;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
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

    @Column(nullable = false, unique = true)
    private String url;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Calender calender;

    @Column(nullable = false)
    private String songTitle;

    @Column(nullable = false)
    private String songUri;

    @ManyToOne
    private Socials social;

    @Column(nullable = false)
    private String contentUri;

    @Builder.Default
    private Integer supportCount = 0;
}
