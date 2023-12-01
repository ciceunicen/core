package com.project.repository;

import com.project.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	
	@Query("SELECT p"
			+ " FROM Project p"
			+ " JOIN p.assistances a"
			+ " JOIN p.needs n"
			+ " WHERE ((p.title IN :values"
			+ " OR p.stage.stage_type IN :values"
			+ " OR a.type IN :values"
			+ " OR n.needType IN :values)"
			+ " AND p.projectManager.id_ProjectManager = :idEntrepreneur)")
	Page<Project> findAll(List<String> values, Pageable pageable, Long idEntrepreneur);
	
	@Query("SELECT p"
			+ " FROM Project p"
			+ " JOIN p.assistances a"
			+ " JOIN p.needs n"
			+ " WHERE ((p.title IN :values"
			+ " OR p.stage.stage_type IN :values"
			+ " OR a.type IN :values"
			+ " OR n.needType IN :values)"
			+ " AND p.is_active = :isActive"
			+ " AND p.projectManager.id_ProjectManager = :idEntrepreneur)")
	Page<Project> findAll(List<String> values, Pageable pageable, Long idEntrepreneur, boolean isActive);

    @Query("select p from Project p inner join p.assistances a inner join p.needs n where " +
            "(p.title in :values) or (p.stage.stage_type in :values) or (a.type in :values) or (n.needType in :values) group by p.id_Project")
    Page<Project> findAll(List<String> values, Pageable pageable);

    //select p from project p where p.title like %?1% SENTENCIA PARA UN FUTURO BUSCADOR POR TEXTO

    @Query("select p from Project p where not exists(select dp from DeletedProject dp where dp.project.id_Project=p.id_Project)")
    Page<Project> getAll(Pageable pageable);

    @Query("select p from Project p where p.id_Project=:id")
    Project getProject(Long id);
    
    @Query(value = "select entrepreneurships_id from project_entrepreneurships where project_id_project=:ID and entrepreneurships_id in (select entrepreneurships_id from project_entrepreneurships where project_id_project=:id)", nativeQuery = true)
    List<Long> inCommonEntrepreneurships(Long ID, Long id);

    @Query(value = "SELECT project_id_project FROM project_entrepreneurships cp WHERE project_id_project=:ID && entrepreneurships_id=:id", nativeQuery = true)
    Long containsEntrepreneurship(Long ID, Long id);
    
    @Query(value = "select * from project p where p.id_project in (select project_id_project from project_entrepreneurships where entrepreneurships_id=:id)", nativeQuery = true)
    List<Project> getProjectsThatContainsEntrepreneurship(Long id);
}
