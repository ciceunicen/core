package com.project.service;

import com.project.DTO.response.DTOReferent;
import org.springframework.stereotype.Component;

import com.project.entities.Referent;
import org.springframework.stereotype.Service;

@Service
public interface ReferentService {
	
	public Referent addReferent(Referent newReferent);


	DTOReferent findByIdproject(Long iDproject);
}
