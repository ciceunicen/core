package com.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.project.entities.Entrepreneur;

@Component
public interface EntrepreneurService {

	public Entrepreneur postEntrepeneur(Entrepreneur e);

    public boolean setActive(Long id);

    Entrepreneur editEntreprenur(Long id,Entrepreneur e);

    boolean existeID(Long id);

    Iterable<Entrepreneur> getEntrepreneurs();
}
