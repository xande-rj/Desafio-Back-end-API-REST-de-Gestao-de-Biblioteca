package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.bookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<bookModel,Long>, JpaSpecificationExecutor<bookModel> {
    boolean existsByTitle(String title);
}
