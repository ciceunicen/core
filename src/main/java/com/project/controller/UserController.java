package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.User;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {

	@Autowired UserServiceImp userService;
	
	@GetMapping()
	public void pantallaInicial() {
		System.out.println("holis");
	}
	
	@PostMapping()
	public User postUser(@RequestBody User user) {
		
		 return userService.postUser(user);
	}
}
