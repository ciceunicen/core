package com.project.service;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface ActionService {

    List<DTOAction> getActions();

    DTOAction getActionById(Long id);

    DTOActionUpdate updateAction(Long id, DTOActionUpdate action);

    List<DTOAction> getAllByFilters(List<String> data);

    Action deleteAction(Long id);

    Optional<Action> findById(Long id);

}
