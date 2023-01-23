package com.project.service;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ProjectManagerService {
    ProjectManager getProjectManager(Long id_ProjectManager);

    Page<ProjectManager> getAll(Pageable pageable);

    Page<Project> getAllProjects(Pageable pageable, Long id_projectManager);

    ProjectManager addProjectManager(ProjectManager pm);
}
