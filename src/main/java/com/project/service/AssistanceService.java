package com.project.service;

import com.project.entities.Assistance;
import org.springframework.stereotype.Component;

import com.project.DTO.DTOAssistance;


@Component
public interface AssistanceService {

	public Iterable<Assistance> getAllAssistances();

	public Assistance postAssistance(DTOAssistance assistance);

}
