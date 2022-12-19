package com.project.repository;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("select pm from ProjectManager pm where pm.id_ProjectManager= :id")
    public ProjectManager getProjectManager(Long id);
}
