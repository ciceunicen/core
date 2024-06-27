package com.project.service.implementation;

import com.project.DTO.response.DTOReferent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Referent;
import com.project.service.ReferentService;
import com.project.repository.ReferentRepository;;

@Service
public class ReferentServiceImp implements ReferentService {
	
	@Autowired
	private ReferentRepository referentRepository;

	@Override
	public Referent addReferent(Referent newReferent) {
		Referent referent = referentRepository.save(newReferent);
		return referent;
	}

	@Override
	public DTOReferent findByIdproject(Long iDproject) {
		Referent referent = referentRepository.findByIdproject(iDproject);
		if(referent ==null){
			 throw new RuntimeException("No existe un referente asignado a este proyecto");
		}
		DTOReferent dto= new DTOReferent(referent);
		return dto;
	}

}
