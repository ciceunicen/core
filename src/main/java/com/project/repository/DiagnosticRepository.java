package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Diagnostic;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    
}
