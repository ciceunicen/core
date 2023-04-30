package com.project.controller;

import com.project.entities.Role;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.entities.User;
import com.project.repository.UserRepository;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {

	
	@Autowired 
	UserServiceImp userService;

	@Autowired
    private UserRepository userRepository;

	
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

	@GetMapping("/auth/email/{email}")
	public boolean existsUserByEmail(@PathVariable String email) {
		return userRepository.existsByEmail(email);
	}
	
	@PutMapping("/{ID}")
    public User update(@RequestBody User user, @PathVariable Long ID){
        return user;
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
