package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.DTOAssistance;
import com.project.entities.Assistance;
import com.project.repository.AssistanceRepository;
import com.project.service.AssistanceService;

@Service
public class AssistanceServiceImp implements AssistanceService {
	@Autowired
    private AssistanceRepository assistanceRepository;

	@Override
	public Iterable<Assistance> getAllAssistances() {
		return assistanceRepository.findAll();
	}

	@Override
	public Assistance postAssistance(DTOAssistance assistance){
		Assistance assistance1 = new Assistance(assistance.getType());
		return assistanceRepository.save(assistance1);
	}


}
