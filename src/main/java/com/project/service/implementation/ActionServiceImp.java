package com.project.service.implementation;

import com.project.DTO.DTOAction;
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

@Service
public class ActionServiceImp implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public Action postAction(DTOAction a) {
        Action action = new Action(a.getTitle(), a.getManager(), a.getState(), a.getDeadline());
        return this.actionRepository.save(action);
    }
}
