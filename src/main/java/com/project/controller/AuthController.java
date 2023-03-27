package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
    private final AuthService service;
	
	public AuthController(AuthService service){
       this.service = service; 
    }
	
	@PostMapping("/login")
	public LoginResultDto login(@RequestBody LoginDto loginDto) {
		// Return placeholder, el metodo es de un proyecto pasado. 
		// TODO: Borrar y remplazar con el metodo real una vez que se decida, o nombrar
		// el metodo que se termine usando lo mismo.
		return service.getJwtToken(loginDto);
	}
}
