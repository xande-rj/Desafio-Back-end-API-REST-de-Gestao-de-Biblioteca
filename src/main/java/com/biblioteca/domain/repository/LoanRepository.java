package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanModel,Long> {
}
