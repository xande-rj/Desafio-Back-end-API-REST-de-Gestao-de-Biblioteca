package com.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@NoArgsConstructor
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false
    )
    private UUID id;

    @Column(
            name = "title",
            nullable = false
    )
    private String titleCategory;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;
}
