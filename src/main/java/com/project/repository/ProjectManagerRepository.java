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

    @Query("select pm.projects from ProjectManager pm where pm.id_ProjectManager= :id_projectManager")
    Page<Project> getAllProjects(Pageable pageable, Long id_projectManager);
}
