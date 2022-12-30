package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Assitance;

@Component
public interface AssitanceService {
	public Iterable<Assitance> getAllAssitances();

}
