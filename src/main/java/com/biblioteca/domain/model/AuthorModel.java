package com.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_author")
@Getter
@Setter
@NoArgsConstructor
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
    name = "id",
    nullable = false,
    unique = true
    )
    private UUID id;

    @Column(name = "name",
    nullable = false
    )
    private String name;

    @Column(name = "details_author",
            nullable = false)
    private String details;

    @Column(name = "created_at",
    nullable = false
    )
    private LocalDateTime createdAt;

}