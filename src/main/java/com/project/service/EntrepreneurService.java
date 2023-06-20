package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Entrepreneur;

import java.util.Optional;

@Component
public interface EntrepreneurService {

	Entrepreneur postEntrepreneur(Entrepreneur e, Long currentUser_id);

    boolean setActive(Long id);

    Entrepreneur editEntrepreneur(Long id,Entrepreneur e, boolean restricted);

    boolean existeID(Long id);

    Iterable<Entrepreneur> getEntrepreneurs();

    Optional<Entrepreneur> getEntrepreneurById(Long id);

    Optional<Entrepreneur> deleteEntrepreneur(Long id);

}
