package com.project.repository;

import com.project.entities.CompositeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositeProjectRepository extends JpaRepository<CompositeProject, Long> {

    @Query(value = "SELECT composite_project_id FROM composite_project_entrepreneurships cp WHERE composite_project_id=:ID && entrepreneurships_id=:id", nativeQuery = true)
    Long containsEntrepreneurship(Long ID, Long id);
}
