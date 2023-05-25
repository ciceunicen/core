package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Entrepreneur;

@Component
public interface EntrepreneurService {

	public Entrepreneur postEntrepeneur(Entrepreneur e);

}
