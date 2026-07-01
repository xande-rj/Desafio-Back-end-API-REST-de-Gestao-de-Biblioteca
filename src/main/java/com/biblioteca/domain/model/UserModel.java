package com.biblioteca.domain.model;

import com.biblioteca.domain.enuns.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String nameUser;

    @Column(
            name = "password",
            nullable = false
    )
    private String passwordUser;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String emailUser;

    @Column(
            name = "removed",
            nullable = false
    )
    private boolean removedUser;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false
    )
    private Roles roleUser;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;

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


    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<LoanModel> loans = new ArrayList<>();

    @OneToMany(
            mappedBy = "historical_user",
            fetch = FetchType.LAZY
    )
    private List<LoanModel> historical= new ArrayList<>();

}
