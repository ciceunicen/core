package com.project.service.implementation;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityUpdate;
import com.project.DTO.DTOCompositeProject;
import com.project.DTO.DTOCompositeProjectInsert;
import com.project.entities.Activity;
import com.project.entities.CompositeProject;
import com.project.entities.Entrepreneurship;
import com.project.repository.CompositeProjectRepository;

import com.project.service.CompositeProjectService;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompositeProjectServiceImp implements CompositeProjectService {

    @Autowired
    private CompositeProjectRepository compositeProjectRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public DTOCompositeProject postCompositeProject(DTOCompositeProjectInsert cp) {
        CompositeProject aux = new CompositeProject(cp.getTitle(), cp.getDescription(), cp.getStart_date());
        aux = compositeProjectRepository.save(aux);
        if (aux != null) {
            DTOCompositeProject dto = new DTOCompositeProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                    aux.getFiles(), aux.getActions(), aux.getEntrepreneurships());
            return dto;
        }
        return null;
    }

    @Override
    public Iterable<DTOCompositeProject> getCompositeProjects() {
        List<DTOCompositeProject> listaDTO = new ArrayList<>();
        Iterable<CompositeProject> projects = this.compositeProjectRepository.findAll();
        for (CompositeProject aux: projects) {
            DTOCompositeProject dto = new DTOCompositeProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                    aux.getFiles(), aux.getActions(), aux.getEntrepreneurships());
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @Override
    public DTOCompositeProject getCompositeProject(Long id) {
        Optional<CompositeProject> o = compositeProjectRepository.findById(id);
        if (o.isPresent()) {
            CompositeProject aux = o.get();
            DTOCompositeProject dto = new DTOCompositeProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                    aux.getFiles(), aux.getActions(), aux.getEntrepreneurships());
            return dto;
        }
        return null;
    }

    @Override
    public CompositeProject getCompositeProjectEntity(Long id) {
        Optional<CompositeProject> o = compositeProjectRepository.findById(id);
        if (o.isPresent()) {
            CompositeProject aux = o.get();
            return aux;
        }
        return null;
    }

    @Override
    public DTOCompositeProject addEntrepreneurship(Long main_project_id, Entrepreneurship e) {
        CompositeProject main_p = this.compositeProjectRepository.findById(main_project_id).get();
        main_p.addEntrepreneurship(e);
        this.compositeProjectRepository.save(main_p);
        return this.getCompositeProject(main_project_id);
    }

    @Override
    public boolean containsEntrepreneurship(Long main_project_id, Long sub_project) {
        return (this.compositeProjectRepository.containsEntrepreneurship(main_project_id, sub_project) != null);
    }

}
