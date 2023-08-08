package com.discoverme.backend.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_id_seq",
            sequenceName = "project_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_id_seq"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String songTitle;

    @Column(nullable = false, unique = true)
    private String artwork;

    @Column(nullable = false, unique = true)
    private String songUri;

    @ManyToOne
    private PromotionPlatform promotionPlatform;

    @Column(nullable = false, unique = true)
    private String instagramUri;

    @Column(nullable = false, unique = true)
    private String tiktokUri;

    @Column(nullable = false, unique = true)
    private String youtubeUri;

    @Column(nullable = false)
    private String password;

    private String role;
    @Column(nullable = false)
    ZonedDateTime createdAt;
}
