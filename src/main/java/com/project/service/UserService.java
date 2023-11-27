package com.project.service;

import com.project.DTO.DTOUserUpdate;
import com.project.entities.Role;
import org.springframework.stereotype.Component;

import com.project.entities.User;

@Component
public interface UserService {
	
	public User postUser(User u);

	public User changeRole(Long id, Role role);

	public User findById(Long id);

	public User updateUser(Long id, DTOUserUpdate updateUser);
}
