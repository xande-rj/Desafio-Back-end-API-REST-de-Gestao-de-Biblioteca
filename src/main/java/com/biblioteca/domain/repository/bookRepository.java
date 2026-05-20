package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.bookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<bookModel,Long> {
    boolean existsByTitle(String title);
}
