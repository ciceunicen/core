package com.project.service;

import com.project.DTO.DTOProjectInsert;
import com.project.entities.Project;
import org.springframework.stereotype.Component;

@Component
public interface ProjectService {

    public Project addProject(Project project);
}
