package com.project.service.implementation;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import com.project.repository.ProjectManagerRepository;
import com.project.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagerServiceImp implements ProjectManagerService {
    @Autowired
    private ProjectManagerRepository projectManagerRepository;


    @Override
    public ProjectManager getProjectManager(Long id_ProjectManager) {
        return projectManagerRepository.getByProjectManagerById(id_ProjectManager);
    }

    @Override
    public Page<ProjectManager> getAll(Pageable pageable) {
        return projectManagerRepository.getAll(pageable);
    }

    @Override
    public Page<Project> getAllProjects(Pageable pageable, Long id_projectManager) {
        return projectManagerRepository.getAllProjects(pageable,id_projectManager);
    }

    @Override
    public ProjectManager addProjectManager(ProjectManager pm) {
        return projectManagerRepository.save(pm);
    }
}
