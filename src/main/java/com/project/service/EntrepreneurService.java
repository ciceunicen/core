package com.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.project.entities.Entrepreneur;

@Component
public interface EntrepreneurService {

	public Entrepreneur postEntrepeneur(Entrepreneur e);

    public ResponseEntity<?> setActive(Long id);
}
