package com.project.service;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import org.springframework.stereotype.Component;


import java.util.Iterator;

import java.util.List;
import java.util.Optional;


@Component
public interface ActionService {

    DTOAction postAction(DTOActionInsert a);

    Iterable<Action> getActions();

    Optional<Action> getActionById(Long id);

    DTOActionUpdate updateAction(Long id, DTOActionUpdate action);

    Action deleteAction(Long id);

    List<DTOAction> getAllByFilters(List<String> data);
}
