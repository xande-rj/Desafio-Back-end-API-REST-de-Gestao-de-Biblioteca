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
@Table(name = "TB_books")
@Getter
@Setter
@NoArgsConstructor
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private Long pages;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean removed;


    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private AuthorModel author;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryModel category;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<LoanModel> loans = new ArrayList<>();
}
