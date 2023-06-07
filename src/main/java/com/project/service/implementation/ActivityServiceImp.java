package com.project.service.implementation;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
import com.project.entities.Activity;
import com.project.repository.ActivityRepository;
import com.project.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;


    @Override
    public DTOActivity postActivity(DTOActivityInsert a) {


        Activity aux = new Activity(a.getTitle(), a.getDescription(), a.getStart_date(), a.getFinish_date());
        aux = this.activityRepository.save(aux);
        DTOActivity dto =  new DTOActivity(aux.getId(), aux.getTitle(), aux.getDescription(), aux.getStart_date(), aux.getFinish_date(), aux.getFiles(), aux.getActions());
        return dto;
    }
}
