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
@Table(name = "TB_user")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;
    private boolean removed;
    private Roles role;
    private LocalDate date_of_birth;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


    @OneToMany(mappedBy = "user")
    private List<LoanModel> loans = new ArrayList<>();


    //    @OneToMany
//    @JoinColumn(name = "historical_id")
//    private List<LoanModel> historical= new ArrayList<>();

}
