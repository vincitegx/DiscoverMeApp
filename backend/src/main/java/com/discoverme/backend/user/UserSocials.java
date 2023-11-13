package com.discoverme.backend.user;

import com.discoverme.backend.project.ProjectApprovalStatus;
import com.discoverme.backend.project.SocialPlatform;
import com.discoverme.backend.project.Socials;
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

//    @ManyToOne
//    @JoinColumn(name = "users_id")
//    private Users user;
//
//    @ManyToOne
//    @JoinColumn(name = "socials_id")
//    private Socials socials;

    @Enumerated(EnumType.STRING)
    private SocialPlatform platform;
    
    @Column(nullable = false, unique = true)
    private String uri;
}
