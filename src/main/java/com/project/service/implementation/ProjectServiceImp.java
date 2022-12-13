package com.project.service.implementation;

import com.project.DTO.DTOProjectInsert;
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
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
}
