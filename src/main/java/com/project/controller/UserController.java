package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UserController {
	
	@Autowired
	private final UserService service;
	
	@GetMapping("/")
    public Iterable<User> getUsers(){
        return service.findAll();
    }
	
	@GetMapping("/{ID}")
    public Iterable<User> getUser(@PathVariable Integer ID){
        return service.findById(ID);
    }
	
	@PostMapping("/")
    public User save(@RequestBody UserDto user){
        return service.save(user);
    }
	
	@PutMapping("/{ID}")
    public User update(@RequestBody UserDto user, @PathVariable Integer ID){
        return service.update(user, ID);
    }
}
