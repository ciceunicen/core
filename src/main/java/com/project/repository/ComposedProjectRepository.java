package com.project.repository;


import com.project.entities.ComposedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposedProjectRepository extends JpaRepository<ComposedProject, Long> {

    @Query(value = "SELECT cp.id FROM `composed_project` cp WHERE cp.id_project = :id", nativeQuery = true)
    Long findByProjectId(Long id);
}
