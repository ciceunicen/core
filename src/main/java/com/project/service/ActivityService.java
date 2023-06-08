package com.project.service;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
import com.project.entities.Entrepreneur;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ActivityService {

    DTOActivity postActivity(DTOActivityInsert a);

    DTOActivity postActivityAction(DTOActionInsert a, Long id);

    DTOActivity getActivity(Long id);

    Iterable<DTOActivity> getActivities();
}
