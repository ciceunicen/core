package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Entrepreneur;
import com.project.service.implementation.EntrepreneurServiceImp;

@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {
	@Autowired EntrepreneurServiceImp entrepreneurService;
	
	@PostMapping()
	public Entrepreneur postEntrepreneur(@RequestBody Entrepreneur e) {
		return entrepreneurService.postEntrepeneur(e);
	}
	
}
