package com.project.service;

import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurInsert;
import com.project.DTO.DTOEntrepreneurUpdate;
import com.project.DTO.DTOProject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrepreneurService {

	DTOEntrepreneur postEntrepreneur(DTOEntrepreneurInsert e, Long currentUser_id);

    boolean setActive(Long id);

    DTOEntrepreneur editEntrepreneur(Long id, DTOEntrepreneurUpdate e, boolean restricted);

    Iterable<DTOEntrepreneur> getEntrepreneurs();

    DTOEntrepreneur getEntrepreneurById(Long id);

    DTOEntrepreneur deleteEntrepreneur(Long id);

    List<DTOEntrepreneur> getAllByFilters(List<String> filters,boolean deleted);

    List<DTOProject> getProjectsByEntrepreneurId(Long id);

}
