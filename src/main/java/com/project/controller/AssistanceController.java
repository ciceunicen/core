package com.project.controller;

import com.project.entities.Assistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.DTOAssistance;
import com.project.service.implementation.AssistanceServiceImp;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("assistances")
public class AssistanceController {
	
	@Autowired
    private AssistanceServiceImp assistanceService;

	public AssistanceController() {
		super();
	}
	
	/*
	 * Obtiene todos las asistencias guardadas en la base de datos 
     * @return Iterable<Assistance>.
     */
	@GetMapping()
    public Iterable<Assistance> getAllAssitances(){
        return assistanceService.getAllAssistances();
    }
	
	/*
	 * Agrega asistencia a la base de datos
	 */
	@PostMapping()
	public Assistance postAssistance(@RequestBody DTOAssistance assistance) {
		return assistanceService.postAssistance(assistance);
	}
	

}
