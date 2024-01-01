package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Referent;

@Component
public interface ReferentService {
	
	public Referent addReferent(Referent newReferent);

}
