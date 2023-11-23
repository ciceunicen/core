package com.project.controller;

import com.project.entities.Role;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.entities.User;
import com.project.exception.UnauthorizedException;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("usuarios")
public class UserController {

	
	@Autowired 
	UserServiceImp userService;
	@Autowired
	RoleAuthController roleAuthController;

	
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
	
	@PutMapping("/{ID}")
    public User update(@RequestBody User user, @PathVariable Long ID){
        return user;
    }
	
	/**
	 * Realiza el borrado lógico de un usuario.
	 * Solo pueden realizarlo un superAdmin
	 * @param ID el identificador del usuario
	 * @return el user borrado
	 * @throws UnauthorizedException
	 */
	@DeleteMapping("/{ID}")
    public ResponseEntity<User> delete(@PathVariable Long ID) {
		if (roleAuthController.hasPermission(1)) {
			return ResponseEntity.ok(userService.deleteUser(ID));
		} else {
			throw new UnauthorizedException();
		}
    }

	/**
	 * Actualiza el rol de un usuario a Administrador
	 * @param id es el id del usuario que va a actualizar
	 * @return retorna el usuario actualizado
	 */
	@PutMapping("/{id}/rol")
	public ResponseEntity< User> updateRole(@PathVariable Long id, @RequestBody Role role){
		if (roleAuthController.hasPermission(1)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.changeRole(id,role));
		} else {
			throw new UnauthorizedException();
		}
	}

}
