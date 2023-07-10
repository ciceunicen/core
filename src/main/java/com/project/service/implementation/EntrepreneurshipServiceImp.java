package com.project.service.implementation;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActivity;
import com.project.DTO.DTOEntrepreneurship;
import com.project.entities.Action;
import com.project.entities.Activity;
import com.project.entities.Entrepreneurship;
import com.project.repository.EntrepreneurshipRepository;
import com.project.service.ActionService;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipServiceImp implements EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;
    @Autowired
    private ActionService actionService;

    @Override
    public List<DTOEntrepreneurship> getAllEntrepreneurships() {
        List<DTOEntrepreneurship> list = new ArrayList<>();
        List<Entrepreneurship> aux = entrepreneurshipRepository.findAll();
        for (Entrepreneurship e: aux) {
            DTOEntrepreneurship dto = new DTOEntrepreneurship(e.getId(), e.getTitle(), e.getDescription(), e.getStart_date(),e.getFiles(),e.getActions());
            list.add(dto);
        }
        return list;
    }

    @Override
    public DTOEntrepreneurship getEntrepreneurship(Long entrepreneurship_id) {
        Optional<Entrepreneurship> o = this.entrepreneurshipRepository.findById(entrepreneurship_id);
        if (o.isPresent()) {
            Entrepreneurship e = o.get();
            DTOEntrepreneurship dto = new DTOEntrepreneurship(e.getId(), e.getTitle(), e.getDescription(), e.getStart_date(),e.getFiles(),e.getActions());
            return dto;
        }
        return null;
    }

    @Override
    public List<DTOEntrepreneurship> getAllByFilters(List<String> data) {
        List<DTOEntrepreneurship> list = new ArrayList<>();
        List<Entrepreneurship> aux = entrepreneurshipRepository.findAll(data);
        for (Entrepreneurship e: aux) {
            DTOEntrepreneurship dto = new DTOEntrepreneurship(e.getId(), e.getTitle(), e.getDescription(), e.getStart_date(),e.getFiles(),e.getActions());
            list.add(dto);
        }
        return list;
    }

    @Override

    public List<DTOActivity> getActivitiesByCompositeProjectIdFiltered(Long cp_id, List<String> data) {
        List<DTOActivity> list = new ArrayList<>();
        List<Activity> aux = this.entrepreneurshipRepository.findAllActivitiesByCompositeProjectId(cp_id, data);
        for (Activity act: aux) {
            DTOActivity actAux = new DTOActivity(act.getId(),act.getTitle(),act.getDescription(),act.getStart_date(),act.getFinish_date(),act.getFiles(),act.getActions());
            list.add(actAux);
        }
        return list;
    }

    @Override
    public Action deleteAction(Long entrepreneurship_id, Long action_id) {
        Optional<Action> o = this.actionService.findById(action_id);
        if (o.isPresent()) {
            Action a = o.get();
            Entrepreneurship e = this.entrepreneurshipRepository.findById(entrepreneurship_id).get();
            e.removeAction(a);
            this.entrepreneurshipRepository.save(e);
            return this.actionService.deleteAction(action_id);
        }
        return null;
    }

    private List<Long> getProjectsContainingEntrepreneurship(Long entrepreneurship_id) {
        return this.entrepreneurshipRepository.containsEntrepreneurship(entrepreneurship_id);
    }

    @Override
    public boolean deleteEntrepreneurship(Long id) {
        List<Long> list = this.getProjectsContainingEntrepreneurship(id);
        if (list == null || list.isEmpty()) {
            this.entrepreneurshipRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<DTOAction> getAllActionByFilters(Long id, List<String> data) {
        List<DTOAction> list = new ArrayList<>();
        List<Action> aux = entrepreneurshipRepository.findActionsEntrepreneurship(id,data);
        for (Action a: aux){
            DTOAction dto = new DTOAction(a.getId(),a.getTitle(),a.getManager(),a.getState(),a.getDeadline());
            list.add(dto);
        }
        return list;
    }
}
