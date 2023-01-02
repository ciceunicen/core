package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.Need;


@Component
public interface NeedService {
	public Iterable<Need> getAllNeeds();
}
