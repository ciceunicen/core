package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.DTOAssistance;
import com.project.entities.Assitance;
import com.project.repository.AssitanceRepository;
import com.project.service.AssitanceService;

@Service
public class AssitanceServiceImp implements AssitanceService{
	@Autowired
    private AssitanceRepository assitanceRepository;

	@Override
	public Iterable<Assitance> getAllAssitances() {
		return assitanceRepository.findAll();
	}

	@Override
	public DTOAssistance postAssistance(Assitance assistance){
		DTOAssistance dto=new DTOAssistance();
		dto.setId_Assitance(assistance.getId_Assitance());
		dto.setType(assistance.getType());
		assitanceRepository.save(assistance);
		return dto;
	}


}
