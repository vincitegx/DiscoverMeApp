package com.discoverme.backend.user;

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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stageName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String facebookUri;

    @Column(nullable = false, unique = true)
    private String twitterUri;

    @Column(nullable = false, unique = true)
    private String instagramUri;

    @Column(nullable = false, unique = true)
    private String tiktokUri;

    @Column(nullable = false, unique = true)
    private String youtubeUri;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean nonLocked;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
