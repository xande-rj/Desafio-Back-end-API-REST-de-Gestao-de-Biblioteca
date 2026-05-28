package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
    boolean existsByNameIgnoreCase(String name);

    Optional<AuthorModel> findByNameIgnoreCase(String name);
}
