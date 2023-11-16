package com.project.service;

import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOCompositeProject;
import com.project.DTO.DTOCompositeProjectInsert;
import com.project.DTO.DTOCompositeProjectUpdate;
import com.project.DTO.DTOProject;
import com.project.entities.CompositeProject;
import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompositeProjectService {

    DTOProject postCompositeProject(DTOCompositeProjectInsert cp);

    Iterable<DTOProject> getCompositeProjects();

    DTOProject getCompositeProject(Long id);

    CompositeProject getCompositeProjectEntity(Long id);

    List<DTOProject> getCompositeProjectsThatContain(Long id);

    DTOProject addEntrepreneurship(Long main_project_id, Entrepreneurship e);

    boolean containsEntrepreneurship(Entrepreneurship mainProject,Entrepreneurship subProject);

    boolean containsCommonEntrepreneurships(Long main_project_id, Long subproject_id);

    DTOProject updateCompositeProject(Long id, DTOCompositeProjectUpdate dto);

    DTOProject postCompositeProjectAction(DTOActionInsert a, Long id);
}
