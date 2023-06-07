package com.project.service.implementation;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import com.project.entities.utils.ActionState;
import com.project.repository.ActionRepository;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Iterator;

import java.util.Optional;


@Service
public class ActionServiceImp implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public Action postAction(DTOAction a) {
        Action action = new Action(a.getTitle(), a.getManager(), a.getState(), a.getDeadline());
        return this.actionRepository.save(action);
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
