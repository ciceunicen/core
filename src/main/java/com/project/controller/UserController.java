package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.entities.User;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {
	
	@Autowired 
	UserServiceImp userService;
	
	@PostMapping()
	public User postUser(@RequestBody User user) {
		
		 return userService.postUser(user);
	}
	
	@GetMapping("/")
    public Iterable<User> getUsers(){
        return userService.findAll();
    }
	
	@GetMapping("/{ID}")
    public Iterable<User> getUser(@PathVariable Integer ID){
        return userService.findById(ID);
    }
	
	@PostMapping("/")
    public User save(@RequestBody UserDto user){
        return userService.save(user);
    }
	
	@PutMapping("/{ID}")
    public User update(@RequestBody UserDto user, @PathVariable Integer ID){
        return userService.update(user, ID);
    }

}
