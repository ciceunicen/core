package com.project.service;

import com.project.DTO.*;
import com.project.entities.Activity;
import com.project.entities.Entrepreneur;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ActivityService {

    DTOActivity postActivity(DTOActivityInsert a);

    DTOActivity postActivityAction(DTOActionInsert a, Long id);

    DTOActivity getActivity(Long id);

    Activity getActivityEntity(Long id);

    DTOActivityUpdate getActivityUpdate(Long id);

    Iterable<DTOActivity> getActivities();

    DTOActivityUpdate updateActivity(Long id, DTOActivityUpdate act);

    DTOActivity deleteActivity(Long id);
}
