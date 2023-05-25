package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Entrepreneur;
import com.project.service.implementation.EntrepreneurServiceImp;

@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {

	@Autowired
	EntrepreneurServiceImp entrepreneurService;
	
	@PostMapping()
	public ResponseEntity<Entrepreneur> postEntrepreneur(@RequestBody Entrepreneur e) {
		return ResponseEntity.status(HttpStatus.CREATED).body(entrepreneurService.postEntrepeneur(e));
	}

}
