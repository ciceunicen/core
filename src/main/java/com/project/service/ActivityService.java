package com.project.service;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
import org.springframework.stereotype.Component;

@Component
public interface ActivityService {

    DTOActivity postActivity(DTOActivityInsert a);

}
