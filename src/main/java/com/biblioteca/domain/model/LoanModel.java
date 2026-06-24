package com.biblioteca.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "historical_user_id")
    private UserModel historical_user;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    @JsonManagedReference
    private BookModel book;
}
