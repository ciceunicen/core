package com.project.service.implementation;

import com.project.DTO.DTOComposedProject;
import com.project.DTO.DTOComposedProjectInsert;
import com.project.DTO.DTOEntrepreneurshipInsert;
import com.project.entities.ComposedProject;
import com.project.entities.Entrepreneurship;
import com.project.entities.Project;
import com.project.repository.ComposedProjectRepository;
import com.project.service.ComposedProjectService;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComposedProjectServiceImp implements ComposedProjectService {

    @Autowired
    private ComposedProjectRepository composedProjectRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public DTOComposedProject postComposedProject(DTOComposedProjectInsert cp) {
        Project p = projectService.getProject(cp.getId_project());
        if (p != null) {
            if(!isComposed(p.getId_Project())) {
                ComposedProject aux = new ComposedProject(cp.getTitle(), cp.getDescription(), cp.getStart_date(), p);
                aux = composedProjectRepository.save(aux);
                DTOComposedProject dto = new DTOComposedProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                        aux.getFiles(), aux.getActions(), aux.getEntrepreneurships(), aux.getProject());
                return dto;
            }
        }
        return null;
    }

    @Override
    public DTOComposedProject getComposedProject(Long id) {
        Optional<ComposedProject> o = composedProjectRepository.findById(id);
        if (o.isPresent()) {
            ComposedProject aux = o.get();
            DTOComposedProject dto = new DTOComposedProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                    aux.getFiles(), aux.getActions(), aux.getEntrepreneurships(), aux.getProject());
            return dto;
        }
        return null;
    }

    @Override
    public DTOComposedProject addEntrepreneurship(Entrepreneurship e, Long id) {
        Optional<ComposedProject> o = composedProjectRepository.findById(id);
        if (o.isPresent()) {
            ComposedProject cp = o.get();
            cp.addEntrepreneurship(e);
            composedProjectRepository.save(cp);
            return getComposedProject(id);
        }
        return null;
    }

    private boolean isComposed(Long id_project) {
        Long cp_id = composedProjectRepository.findByProjectId(id_project);
        return cp_id != null;
    }

}
