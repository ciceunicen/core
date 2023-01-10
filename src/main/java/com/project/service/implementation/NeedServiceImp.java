package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.DTONeed;
import com.project.entities.Need;
import com.project.repository.NeedRepository;
import com.project.service.NeedService;

@Service
public class NeedServiceImp implements NeedService{
	
	@Autowired
    private NeedRepository needRepository;

	@Override
	public Iterable<Need> getAllNeeds() {
		return needRepository.findAll();
	}

	@Override
	public DTONeed postNeed(Need needs) {
		DTONeed dto=new DTONeed();
		dto.setId_Need(needs.getId_Need());
		dto.setNeedType(needs.getNeedType());
		needRepository.save(needs);
		return dto;
	}

}
