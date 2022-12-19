package com.project.service;

import com.project.entities.ProjectManager;
import org.springframework.stereotype.Component;

@Component
public interface ProjectManagerService {
    ProjectManager getProjectManager(Long id_ProjectManager);
}
