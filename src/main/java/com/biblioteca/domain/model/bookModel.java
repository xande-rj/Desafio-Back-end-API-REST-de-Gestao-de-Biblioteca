package com.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_author")
@Getter
@Setter
@NoArgsConstructor
public class bookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String details;
    private Long pages;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
//    private status;
//
//    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id",nullable = false)
//    private authorModel author;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id",nullable = false)
//    private List<categoryModel> category;
}
