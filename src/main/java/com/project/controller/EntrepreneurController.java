package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {
	@Autowired
	private final EntrepreneurService service;
	
	@PostMapping("/")
    public Entrepreneur save(@RequestBody EntrepreneurDto entrepreneur){
        return service.save(entrepreneur);
    }
}
