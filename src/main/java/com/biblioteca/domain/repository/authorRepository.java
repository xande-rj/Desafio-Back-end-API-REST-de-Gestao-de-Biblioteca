package com.biblioteca.domain.repository;

import com.biblioteca.domain.model.authorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface authorRepository extends JpaRepository<authorModel, UUID> {
}
