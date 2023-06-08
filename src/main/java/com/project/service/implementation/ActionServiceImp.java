package com.project.service.implementation;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import com.project.repository.ActionRepository;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ActionServiceImp implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public DTOAction postAction(DTOActionInsert a) {
        Action aux = new Action(a.getTitle(), a.getManager(), a.getState(), a.getDeadline());
        aux = this.actionRepository.save(aux);
        DTOAction dto = new DTOAction(aux.getId(), aux.getTitle(), aux.getManager(), aux.getState(), aux.getDeadline());
        return dto;
    }

    @Override
    public Iterable<Action> getActions() {
        return this.actionRepository.findAll();
    }
    public Optional<Action> getActionById(Long id) {
        return this.actionRepository.findById(id);

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
}
