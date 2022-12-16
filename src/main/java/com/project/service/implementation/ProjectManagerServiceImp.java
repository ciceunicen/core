package com.project.service.implementation;

import com.project.entities.ProjectManager;
import com.project.repository.ProjectManagerRepository;
import com.project.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagerServiceImp implements ProjectManagerService {
    @Autowired
    private ProjectManagerRepository projectManagerRepository;


    @Override
    public ProjectManager getProjectManager(Long id_ProjectManager) {
        return projectManagerRepository.getByProjectManagerById(id_ProjectManager);
    }
}
