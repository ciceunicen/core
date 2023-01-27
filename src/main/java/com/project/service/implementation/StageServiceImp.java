package com.project.service.implementation;
import com.project.entities.Stage;
import com.project.repository.StageRepository;
import com.project.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImp implements StageService {
	
	@Autowired
    private StageRepository stageRepository;

	@Override
	public Iterable<Stage> getAllStages() {
		return stageRepository.findAll();
	}

	@Override
	public Stage getStage(Long id) {
		return stageRepository.getStage(id);
	}
}
