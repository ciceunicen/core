package com.project.service;

import com.project.DTO.DTOProjectInsert;
import com.project.entities.Project;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface ProjectService {

    public Project addProject(Project project,Long id_ProjectManager);

    public Optional<Project> getProjectById(Long id);
}
