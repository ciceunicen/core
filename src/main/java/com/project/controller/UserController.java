package com.project.controller;

import com.project.entities.Role;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.entities.User;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {

	@Autowired UserServiceImp userService;
	
	@PostMapping()
	public User postUser(@RequestBody User user) {
		
		 return userService.postUser(user);
	}

	/**
	 * Actualiza el rol de un usuario a Administrador
	 * @param id es el id del usuario que va a actualizar
	 * @return retorna el usuario actualizado
	 */
	@PutMapping("/{id}/rol")
	public User updateRole(@PathVariable Long id, @RequestBody Role role){

		return userService.changeRole(id,role);
	}
}
