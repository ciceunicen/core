package com.project.repository;

import com.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositeProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "select entrepreneurships_id from composite_project_entrepreneurships where composite_project_id=:ID and entrepreneurships_id in (select entrepreneurships_id from composite_project_entrepreneurships where composite_project_id=:id)", nativeQuery = true)
    List<Long> inCommonEntrepreneurships(Long ID, Long id);

    @Query(value = "SELECT composite_project_id FROM composite_project_entrepreneurships cp WHERE composite_project_id=:ID && entrepreneurships_id=:id", nativeQuery = true)
    Long containsEntrepreneurship(Long ID, Long id);

    @Query(value = "select * from composite_project cp where cp.id in (select composite_project_id from composite_project_entrepreneurships where entrepreneurships_id=:id)", nativeQuery = true)
    List<Project> getCompositeProjectsThatContainsEntrepreneurship(Long id);

}
