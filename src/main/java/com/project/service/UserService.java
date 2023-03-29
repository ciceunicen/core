package com.project.service;

import org.springframework.stereotype.Component;

import com.project.entities.User;

@Component
public interface UserService {
	
	public User postUser(User u);

	public User makeAdmin(User u);

}
