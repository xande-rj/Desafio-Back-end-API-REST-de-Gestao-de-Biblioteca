package com.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_loans")
@Getter
@Setter
@NoArgsConstructor
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime create_at;
    private LocalDateTime pay_day;
    private LocalDateTime update_at;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private BookModel book;
}
