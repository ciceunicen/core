package com.project.repository;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager,Long> {
    @Query("select pm from ProjectManager pm where pm.id_ProjectManager =  :idProjectManager")
    public ProjectManager getByProjectManagerById(Long idProjectManager);

    @Query("SELECT pm from ProjectManager pm")
    Page<ProjectManager> getAll(Pageable pageable);

    @Query("select p from Project p where p.projectManager.id_ProjectManager= :id_projectManager and not exists(select dp from DeletedProject dp where dp.project.id_Project=p.id_Project)")
    Page<Project> getAllProjects(Pageable pageable, Long id_projectManager);
}
