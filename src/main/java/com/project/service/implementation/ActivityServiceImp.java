package com.project.service.implementation;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
import com.project.entities.Action;
import com.project.entities.Activity;
import com.project.repository.ActionRepository;
import com.project.repository.ActivityRepository;
import com.project.service.ActionService;
import com.project.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public DTOActivity postActivity(DTOActivityInsert a) {


        Activity aux = new Activity(a.getTitle(), a.getDescription(), a.getStart_date(), a.getFinish_date());
        aux = this.activityRepository.save(aux);
        DTOActivity dto =  new DTOActivity(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date(), aux.getFiles(), aux.getActions());
        return dto;
    }

    @Override
    public DTOActivity postActivityAction(DTOActionInsert a, Long id) {
        Action act = new Action(a.getTitle(), a.getManager(), a.getState(), a.getDeadline());
        Activity aux = activityRepository.findById(id).get();
        aux.addAction(act);
        DTOActivity dto =  new DTOActivity(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date(), aux.getFiles(), aux.getActions());
        activityRepository.save(aux);
        return dto;
    }

    @Override
    public DTOActivity getActivity(Long id) {
        Optional<Activity> act = activityRepository.findById(id);
        if (!act.isEmpty()) {
            Activity aux = act.get();
            DTOActivity dto =  new DTOActivity(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date(), aux.getFiles(), aux.getActions());
            return dto;
        }
        return null;
    }

}
