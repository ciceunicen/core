package com.project.repository;

import com.project.entities.AdministrationRecords;
import com.project.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p inner join p.assistances a inner join p.needs n where " +
            "(p.title in :values) or (p.stage.stage_type in :values) or (a.type in :values) or (n.needType in :values) group by p.id_Project")
    public Page<Project> findAll(List<String> values, Pageable pageable);

    //select p from project p where p.title like %?1% SENTENCIA PARA UN FUTURO BUSCADOR POR TEXTO

    @Query("select p from Project p where not exists(select dp from DeletedProject dp where dp.project.id_Project=p.id_Project)")
    public Page<Project> getAll(Pageable pageable);
}
