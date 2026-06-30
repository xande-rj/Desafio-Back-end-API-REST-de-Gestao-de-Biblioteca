package com.biblioteca.domain.model;

import com.biblioteca.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_books")
@Getter
@Setter
@NoArgsConstructor
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            unique = true
    )
    private String titleBook;

    @Column(
            name = "details",
            nullable = false
    )
    private String detailsBook;

    @Column(
            name = "pages",
            nullable = false
    )
    private Long pagesBook;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    private Status statusBook;

    @Column(
            name = "removed",
            nullable = false
    )
    private boolean removedBook;

    @ManyToOne
    @JoinColumn(
            name = "author_id",
            nullable = false
    )
    private AuthorModel author;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private CategoryModel category;

}
