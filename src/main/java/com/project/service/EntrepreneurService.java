package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Entrepreneur;

import java.util.Optional;

@Component
public interface EntrepreneurService {

	public Entrepreneur postEntrepeneur(Entrepreneur e);

    public boolean setActive(Long id);

    Entrepreneur editEntreprenur(Long id,Entrepreneur e);

    boolean existeID(Long id);

    Optional<Entrepreneur> getEntrepreneurById(Long id);
}
