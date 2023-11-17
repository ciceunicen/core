package com.project.controller;

import com.project.DTO.DTOUserUpdate;
import com.project.entities.Role;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.entities.User;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {

	
	@Autowired 
	UserServiceImp userService;

	
	@PostMapping()
	public ResponseEntity<User> postUser(@RequestBody @Valid User user) {	
		 return ResponseEntity.status(HttpStatus.CREATED).body(userService.postUser(user));
	}
	
	@GetMapping()
    public ResponseEntity<Iterable<User>>getUsers(@RequestParam(required = false) List<String> rolIds){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(rolIds));
    }
	
	@GetMapping("/{ID}")
    public ResponseEntity<User> getUser(@PathVariable Long ID){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(ID));
    }

	/**
	 * Actualiza los datos del usuario
	 * @param updatedUser usuario actualizado
	 * @param ID es el id del usuario que va a actualizar sus datos
	 * @return usuario actualizado con estado HTTP OK
	 */
	@PutMapping("/{ID}")
    public ResponseEntity<User> update(@RequestBody DTOUserUpdate updatedUser, @PathVariable Long ID){
		User updatedUserData = userService.updateUser(ID, updatedUser);
		// Devuelve el usuario actualizado con el código de estado HTTP OK
		return ResponseEntity.status(HttpStatus.OK).body(updatedUserData);
    }

	/**
	 * Actualiza el rol de un usuario a Administrador
	 * @param id es el id del usuario que va a actualizar
	 * @return retorna el usuario actualizado
	 */
	@PutMapping("/{id}/rol")
	public ResponseEntity< User> updateRole(@PathVariable Long id, @RequestBody Role role){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.changeRole(id,role));
	}

}
