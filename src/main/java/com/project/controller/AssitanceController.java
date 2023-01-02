package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Assitance;
import com.project.service.implementation.AssitanceServiceImp;


/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("assitances")
public class AssitanceController {
	
	@Autowired
    private AssitanceServiceImp assitanceService;

	public AssitanceController() {
		super();
	}
	
	/*
	 * Obtiene todos las asistencias guardadas en la base de datos 
     * @return Iterable<Assitance>.
     */
	@GetMapping()
    public Iterable<Assitance> getAllAssitances(){
        return assitanceService.getAllAssitances();
    }
	

}
