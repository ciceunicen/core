package com.project.service.implementation;

import com.project.DTO.*;
import com.project.entities.Action;
import com.project.entities.CompositeProject;
import com.project.entities.Entrepreneurship;
import com.project.repository.CompositeProjectRepository;

import com.project.service.CompositeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompositeProjectServiceImp implements CompositeProjectService {

    @Autowired
    private CompositeProjectRepository compositeProjectRepository;

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
    public boolean containsEntrepreneurship(Entrepreneurship mainProject,Entrepreneurship subProject) {
        return (mainProject.containsEntrepreneurship(subProject));
    }

    @Override
    public boolean containsCommonEntrepreneurships(Long main_project_id, Long subproject_id) {
        return !this.compositeProjectRepository.inCommonEntrepreneurships(main_project_id, subproject_id).isEmpty();
    }

    @Override
    public DTOCompositeProject updateCompositeProject(Long id, DTOCompositeProjectUpdate dto) {
        CompositeProject cp = this.getCompositeProjectEntity(id);
        if (cp != null) {
            if (dto.getTitle() != null) cp.setTitle(dto.getTitle());
            if (dto.getDescription() != null) cp.setDescription(dto.getDescription());
            if (dto.getStart_date() != null) cp.setStart_date(dto.getStart_date());
            this.compositeProjectRepository.save(cp);
            return this.getCompositeProject(id);
        }
        return null;
    }

    @Override
    public DTOCompositeProject postCompositeProjectAction(DTOActionInsert a, Long id) {
        Action act = new Action(a.getTitle(), a.getManager(), a.getState(), a.getDeadline());
        CompositeProject aux = this.getCompositeProjectEntity(id);
        if (aux != null) {
            aux.addAction(act);
            this.compositeProjectRepository.save(aux);
            DTOCompositeProject dto = new DTOCompositeProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                    aux.getFiles(), aux.getActions(), aux.getEntrepreneurships());
            return dto;
        }
        return null;
    }

    @Override
    public List<DTOCompositeProject> getCompositeProjectsThatContain(Long id) {
        List<DTOCompositeProject> list = new ArrayList<>();
        List<CompositeProject> projects = this.compositeProjectRepository.getCompositeProjectsThatContainsEntrepreneurship(id);
        if (projects != null) {
            for (CompositeProject aux: projects) {
                DTOCompositeProject dto = new DTOCompositeProject(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(),
                        aux.getFiles(), aux.getActions(), aux.getEntrepreneurships());
                list.add(dto);
            }
            return list;
        }
        return null;
    }

}
