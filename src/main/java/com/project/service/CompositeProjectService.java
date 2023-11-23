package com.project.service;

import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOProject;
import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectUpdate;
import com.project.entities.Project;
import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompositeProjectService {

    DTOProject postCompositeProject(DTOProjectInsert cp);

    Iterable<DTOProject> getCompositeProjects();

    DTOProject getCompositeProject(Long id);

    Project getCompositeProjectEntity(Long id);

    List<DTOProject> getCompositeProjectsThatContain(Long id);

    DTOProject addEntrepreneurship(Long main_project_id, Entrepreneurship e);

    boolean containsEntrepreneurship(Entrepreneurship mainProject,Entrepreneurship subProject);

    boolean containsCommonEntrepreneurships(Long main_project_id, Long subproject_id);

    DTOProject updateCompositeProject(Long id, DTOProjectUpdate dto);

    DTOProject postCompositeProjectAction(DTOActionInsert a, Long id);
}
