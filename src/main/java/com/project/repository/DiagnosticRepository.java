package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Diagnostic;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

    @Query("SELECT d FROM Diagnostic d WHERE d.idRecord = :id")
    public Optional<Diagnostic> findByIdRecord(Long id);
    
}