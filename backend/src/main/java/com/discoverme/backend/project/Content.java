package com.discoverme.backend.project;

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
public class Content {

    @Id
    @SequenceGenerator(
            name = "content_id_seq",
            sequenceName = "project_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "content_id_seq"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String contentUri;
}
