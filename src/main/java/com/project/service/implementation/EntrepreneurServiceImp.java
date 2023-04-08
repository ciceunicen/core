package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Entrepreneur;
import com.project.repository.EntrepreneurRepository;
import com.project.service.EntrepreneurService;
@Service
public class EntrepreneurServiceImp  implements EntrepreneurService{
	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	@Override
	public Entrepreneur postEntrepeneur(Entrepreneur e) {
		// TODO Auto-generated method stub
		System.out.println(e.getCuil_cuit());
		return entrepreneurRepository.save(e);
	}



}
