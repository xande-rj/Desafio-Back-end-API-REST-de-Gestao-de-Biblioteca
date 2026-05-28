package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long>, JpaSpecificationExecutor<BookModel> {
    boolean existsByTitle(String title);
}
