package com.discoverme.backend.user.social;

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
public class UserSocials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "social_id")
    private Socials social;

    @Column(nullable = false)
    private String socialUserName;

    @Column(nullable = false)
    private String socialUserId;

    @Column(nullable = false)
    private String accessToken;
}
