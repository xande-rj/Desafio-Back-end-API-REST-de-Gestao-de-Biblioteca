package com.biblioteca.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_loans")
@Getter
@Setter
@NoArgsConstructor
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private Long id;

    @Column(
            name = "create_at",
            nullable = false,
            unique = true
    )
    private LocalDateTime createAt;

    @Column(
            name = "pay_day",
            nullable = false
    )
    private LocalDateTime payDay;

    @Column(
            name = "update_at",
            nullable = false,
            unique = true
    )
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserModel user;

    @ManyToOne
    @JoinColumn(
            name = "historical_user_id",
            nullable = false
    )
    private UserModel historical_user;

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false
    )
    private BookModel book;
}
