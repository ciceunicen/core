package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.DeletedProject;

@Repository
public interface DeletedProjectRepository extends JpaRepository<DeletedProject,Long>{
	@Query("SELECT dp FROM DeletedProject dp WHERE dp.project.id_Project = :id_project ")
    public DeletedProject getDeletedProjectByIdProject(Long id_project);
}
