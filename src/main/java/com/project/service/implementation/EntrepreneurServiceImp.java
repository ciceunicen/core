package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.entities.Entrepreneur;
import com.project.entities.User;
import com.project.repository.EntrepreneurRepository;
import com.project.service.EntrepreneurService;
@Service
public class EntrepreneurServiceImp  implements EntrepreneurService{
	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	@Override
	public Entrepreneur postEntrepeneur(Entrepreneur e) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		e.setId_user(u.getId());
		return entrepreneurRepository.save(e);
	}



}
