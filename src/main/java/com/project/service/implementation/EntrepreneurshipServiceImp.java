package com.project.service.implementation;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOEntrepreneurship;
import com.project.entities.Activity;
import com.project.entities.Entrepreneurship;
import com.project.repository.EntrepreneurshipRepository;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EntrepreneurshipServiceImp implements EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Override
    public List<DTOEntrepreneurship> getAllByFilters(List<String> data) {
        List<DTOEntrepreneurship> list = new ArrayList<>();
        List<Entrepreneurship> aux = entrepreneurshipRepository.findAll(data);
        for (Entrepreneurship e: aux) {
            DTOEntrepreneurship dto = new DTOEntrepreneurship(e.getId(), e.getTitle(), e.getDescription(), e.getStart_date(),e.getFiles(),e.getActions());
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<DTOActivity> getActivitiesByCompositeProjectIdFiltered(Long cp_id, List<String> data) {
        List<DTOActivity> list = new ArrayList<>();
        List<Activity> aux = this.entrepreneurshipRepository.findAllActivitiesByCompositeProjectId(cp_id, data);
        for (Activity act: aux) {
            DTOActivity actAux = new DTOActivity(act.getId(),act.getTitle(),act.getDescription(),act.getStart_date(),act.getFinish_date(),act.getFiles(),act.getActions());
            list.add(actAux);
        }
        return list;
    }
}
