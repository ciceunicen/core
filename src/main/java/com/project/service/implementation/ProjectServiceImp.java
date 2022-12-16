package com.project.service.implementation;

import com.project.entities.Project;
import com.project.repository.ProjectRepository;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project, Long id_ProjectManager) {
        project.setProjectManager(projectRepository.getProjectManager(id_ProjectManager));
        return projectRepository.save(project);
    }
}
