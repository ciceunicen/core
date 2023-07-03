package com.project.service;

import com.project.DTO.DTOCompositeProject;
import com.project.DTO.DTOCompositeProjectInsert;
import com.project.DTO.DTOCompositeProjectUpdate;
import com.project.entities.CompositeProject;
import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

@Component
public interface CompositeProjectService {

    DTOCompositeProject postCompositeProject(DTOCompositeProjectInsert cp);

    Iterable<DTOCompositeProject> getCompositeProjects();

    DTOCompositeProject getCompositeProject(Long id);

    CompositeProject getCompositeProjectEntity(Long id);

    DTOCompositeProject addEntrepreneurship(Long main_project_id, Entrepreneurship e);

    boolean containsEntrepreneurship(Entrepreneurship mainProject,Entrepreneurship subProject);

    DTOCompositeProject updateCompositeProject(Long id, DTOCompositeProjectUpdate dto);
}
