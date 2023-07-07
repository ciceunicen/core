package com.project.service.implementation;

import com.project.DTO.*;
import com.project.entities.Action;
import com.project.entities.Activity;
import com.project.repository.ActionRepository;
import com.project.repository.ActivityRepository;
import com.project.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Activity aux = this.getActivityEntity(id);
        if (aux != null) {
            aux.addAction(act);
            DTOActivity dto = new DTOActivity(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date(), aux.getFiles(), aux.getActions());
            activityRepository.save(aux);
            return dto;
        }
        return null;
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

    public Activity getActivityEntity(Long id) {
        Optional<Activity> act = activityRepository.findById(id);
        if (act.isPresent()) return act.get();
        return null;
    }

    @Override
    public DTOActivityUpdate getActivityUpdate(Long id) {
        Optional<Activity> act = activityRepository.findById(id);
        if (!act.isEmpty()) {
            Activity aux = act.get();
            DTOActivityUpdate dto =  new DTOActivityUpdate (aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date());
            return dto;
        }
        return null;
    }

    @Override
    public Iterable<DTOActivity> getActivities() {
        List<DTOActivity> listaDTO = new ArrayList<DTOActivity>();
        Iterable<Activity> actividades = this.activityRepository.findAll();
        for (Activity act :actividades) {
            DTOActivity dto =  new DTOActivity(act.getId(), act.getTitle(), act.getDescription(), act.getStart_date(), act.getFinish_date(), act.getFiles(), act.getActions());
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @Override
    public DTOActivityUpdate updateActivity(Long id, DTOActivityUpdate activity) {
        Activity act = activityRepository.findById(id).get();
        act.setTitle(activity.getTitle());
        act.setDescription(activity.getDescription());
        act.setStart_date(activity.getStart_date());
        act.setFinish_date(activity.getFinish_date());
        activityRepository.save(act);
        return activity;
    }

    @Override
    public DTOActivity deleteActivity(Long id) {
        Activity act = activityRepository.findById(id).get();
        DTOActivity actAux = new DTOActivity(act.getId(),act.getTitle(),act.getDescription(),act.getStart_date(),act.getFinish_date(),act.getFiles(),act.getActions());
        activityRepository.delete(act);
        return actAux;
    }

    @Override
    public List<DTOActivity> getAllByFilters(List<String> data) {
        List<DTOActivity> list = new ArrayList<>();
        List<Activity> aux = activityRepository.findAll(data);
        for (Activity act: aux) {
            DTOActivity actAux = new DTOActivity(act.getId(),act.getTitle(),act.getDescription(),act.getStart_date(),act.getFinish_date(),act.getFiles(),act.getActions());
            list.add(actAux);
        }
        return list;
    }

}
