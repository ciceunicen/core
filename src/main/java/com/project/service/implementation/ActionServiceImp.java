package com.project.service.implementation;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import com.project.repository.ActionRepository;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ActionServiceImp implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<DTOAction> getActions() {
        List<DTOAction> list = new ArrayList<>();
        List<Action> aux = actionRepository.findAll();
        for (Action e: aux) {
            DTOAction dto = new DTOAction(e.getId(), e.getTitle(), e.getManager(), e.getState(), e.getDeadline());
            list.add(dto);
        }
        return list;
    }

    @Override
    public DTOAction getActionById(Long id) {
        Optional<Action> opt = this.actionRepository.findById(id);
        if(opt.isPresent()) {
            Action e = opt.get();
            DTOAction dto = new DTOAction(e.getId(), e.getTitle(), e.getManager(), e.getState(), e.getDeadline());
            return dto;
        }
        return null;
    }

    @Override
    public List<DTOAction> getAllByFilters(List<String> filters) {
        List<DTOAction> list = new ArrayList<>();
        List<Action> aux = actionRepository.findAll(filters);
        for (Action e: aux) {
            DTOAction dto = new DTOAction(e.getId(), e.getTitle(), e.getManager(), e.getState(), e.getDeadline());
            list.add(dto);
        }
        return list;
    }

    @Override
    public DTOActionUpdate updateAction(Long id, DTOActionUpdate action) {
        Action act = actionRepository.findById(id).get();
        act.setTitle(action.getTitle());
        act.setManager(action.getManager());
        act.setState(action.getState());
        act.setDeadline(action.getDeadline());
        actionRepository.save(act);
        return action;
    }

    @Override
    public Action deleteAction(Long id) {
        Action act = actionRepository.findById(id).get();
        Action actAux = act;
        actionRepository.delete(act);
        return actAux;
    }

    @Override
    public Optional<Action> findById(Long id) {
        return this.actionRepository.findById(id);
    }

}
