package com.project.service;

import com.project.DTO.request.DtoUpdateDataUser;
import com.project.DTO.response.DTOeditableData;
import com.project.entities.Role;
import org.springframework.stereotype.Component;

import com.project.entities.User;

@Component
public interface UserService {
	
	public User postUser(User u);

	public User changeRole(Long id, Role role);


    DTOeditableData getUpdatableData(String email);

    User updateUserInformation(Long id, DtoUpdateDataUser newDataUser);
}
