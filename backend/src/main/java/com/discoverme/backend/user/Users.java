package com.discoverme.backend.user;

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
@Table(name = "users",uniqueConstraints = {
    @UniqueConstraint(  name = "user_phone_unique", columnNames = "phoneNumber")
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String role;
    @Column(nullable = false)
    ZonedDateTime createdAt;

    @Column(nullable = false)
    private Boolean nonLocked;

    @Column(nullable = false)
    private Boolean enabled;
    public Users(String stageName, String phoneNumber, String password, String role) {
        this.stageName = stageName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users that = (Users) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
