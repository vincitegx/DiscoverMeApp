package com.discoverme.backend.project;

import com.discoverme.backend.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private ProjectCalender calender;

    @Column(nullable = false, unique = true)
    private String songTitle;

    @Column(nullable = false, unique = true)
    private String artworkUri;

    @Column(nullable = false, unique = true)
    private String songUri;

    @OneToMany
    private Set<Socials> socials;

    @OneToMany
    private Set<Content> content;
    @Enumerated(EnumType.STRING)
    private ProjectApprovalStatus status;

    @Builder.Default
    private Integer voteCount = 0;

    @Builder.Default
    private Integer supportCount = 0;
}
