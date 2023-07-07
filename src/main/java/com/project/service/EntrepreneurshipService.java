package com.project.service;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOEntrepreneurship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrepreneurshipService {

    List<DTOEntrepreneurship> getAllByFilters(List<String> data);

    List<DTOActivity> getActivitiesByCompositeProjectIdFiltered(Long cp_id, List<String> data);

}
