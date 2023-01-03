package com.project.service;

import com.project.entities.Need;
import com.project.entities.Stage;
import org.springframework.stereotype.Component;


@Component
public interface StageService {
	public Iterable<Stage> getAllStages();
}
