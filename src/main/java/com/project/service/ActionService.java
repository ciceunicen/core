package com.project.service;

import com.project.DTO.DTOAction;
import com.project.entities.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ActionService {

    Action postAction(DTOAction a);

    Optional<Action> getActionById(Long id);
}
