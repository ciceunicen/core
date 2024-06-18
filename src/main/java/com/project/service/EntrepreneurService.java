package com.project.service;

import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurInsert;
import com.project.DTO.DTOEntrepreneurUpdate;
import com.project.DTO.DTOProject;
import com.project.entities.Entrepreneur;
import org.springframework.data.domain.Page;
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

    Page<DTOProject> getProjectsByEntrepreneurId(Long id, Integer page);


    List<DTOEntrepreneur> getEntrepreneursSolicitudes(Long offset);

    int getTotalPages();


    Entrepreneur getEntrepreneurSolicitud(Long idUsuario);
}
