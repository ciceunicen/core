package com.project.repository;

import com.project.entities.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager,Long> {
    @Query("select pm from ProjectManager pm where pm.id_ProjectManager =  :idProjectManager")
    public ProjectManager getByProjectManagerById(Long idProjectManager);
}
