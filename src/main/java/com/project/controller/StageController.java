package com.project.controller;

import com.project.entities.Need;
import com.project.entities.Stage;
import com.project.service.implementation.NeedServiceImp;
import com.project.service.implementation.StageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("stages")
public class StageController {
	@Autowired
    private StageServiceImp stageService;

	public StageController() {
	}
	/*
	 * Obtiene todos los estadios guardados en la base de datos
     * @return Iterable<Stage>.
     */
	@GetMapping()
    public Iterable<Stage> getAllStages(){
        return stageService.getAllStages();
    }
	
}
