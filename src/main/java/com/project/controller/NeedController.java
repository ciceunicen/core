package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Need;
import com.project.entities.Project;
import com.project.service.implementation.NeedServiceImp;
import com.project.service.implementation.ProjectServiceImp;

/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("needs")
public class NeedController {
	@Autowired
    private NeedServiceImp needService;

	public NeedController() {
		super();
	}
	/*
	 * Obtiene todos las necesidades guardadas en la base de datos 
     * @return Iterable<Need>.
     */
	@GetMapping()
    public Iterable<Need> getAllNeeds(){
        return needService.getAllNeeds();
    }
	
}
