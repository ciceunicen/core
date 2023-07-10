package com.project.service;


import com.project.DTO.DTOActivity;

import com.project.DTO.DTOAction;

import com.project.DTO.DTOEntrepreneurship;
import com.project.entities.Action;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrepreneurshipService {

    List<DTOEntrepreneurship> getAllEntrepreneurships();

    DTOEntrepreneurship getEntrepreneurship(Long entrepreneurship_id);

    List<DTOEntrepreneurship> getAllByFilters(List<String> data);


    List<DTOActivity> getActivitiesByCompositeProjectIdFiltered(Long cp_id, List<String> data);

    Action deleteAction(Long entrepreneurship_id, Long action_id);

    boolean deleteEntrepreneurship(Long id);


    List<DTOAction> getAllActionByFilters(Long id, List<String> data);

}
