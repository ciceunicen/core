package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
