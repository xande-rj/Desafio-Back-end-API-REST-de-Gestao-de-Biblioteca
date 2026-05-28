package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.categoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface categoryRepository extends JpaRepository<categoryModel, UUID> {
     boolean existsByTitleIgnoreCase(String name);

     Optional<categoryModel> findByTitleIgnoreCase(String name);
}
