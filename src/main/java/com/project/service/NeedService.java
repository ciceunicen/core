package com.project.service;

import org.springframework.stereotype.Component;

import com.project.DTO.DTONeed;
import com.project.entities.Need;
import com.project.repository.NeedRepository;


@Component
public interface NeedService {

	public Iterable<Need> getAllNeeds();

	public DTONeed postNeed(Need needs);
}
