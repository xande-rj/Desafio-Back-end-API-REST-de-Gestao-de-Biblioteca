package com.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_user")
@Getter
@Setter
@NoArgsConstructor
public class userModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String password;
    //private List<loanModel> loan= new ArrayList<>();
    private String cpf;
    private LocalDate date_of_birth;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
