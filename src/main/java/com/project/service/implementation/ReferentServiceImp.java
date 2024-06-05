package com.project.service.implementation;

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

}
