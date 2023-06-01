package com.project.controller;

import com.project.entities.User;
import com.project.repository.EntrepreneurRepository;
import com.project.repository.UserRepository;
import org.hibernate.hql.internal.classic.AbstractParameterInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.project.entities.Entrepreneur;
import com.project.service.implementation.EntrepreneurServiceImp;

import java.util.Optional;

@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {

	@Autowired
	EntrepreneurServiceImp entrepreneurService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	
	@PostMapping()
	public ResponseEntity<Entrepreneur> postEntrepreneur(@RequestBody Entrepreneur e) {
		return ResponseEntity.status(HttpStatus.CREATED).body(entrepreneurService.postEntrepeneur(e));
	}

	@PutMapping("/{ID}/validado")
	public ResponseEntity<?> validateEntrepeneur(@PathVariable Long ID){
		if (entrepreneurService.setActive(ID)) {
			return new ResponseEntity("Emprendedor validado con exito",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No tiene permisos de administrador",HttpStatus.UNAUTHORIZED);
		}
	}
	@PutMapping("/editar/{ID}")
	public ResponseEntity<Entrepreneur> editEntreprenur(@RequestBody Entrepreneur e,@PathVariable Long ID) {
		/**
		 * Valido si existe el ID que quiere modificar
		 */
		if (entrepreneurService.existeID(ID)) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User usuario = userRepository.findById(u.getId()).get();
			/**
			 * Si es un usuario defecto o emprendedor SOLO puede modificar su perfil sino se arroja msj y un UNAUTHORIZED
			 */
			if (usuario.getRole().getType().toLowerCase().equals("defecto") || usuario.getRole().getType().toLowerCase().equals("emprendedor")){
				if (usuario.getId() == ID){
					return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.editEntreprenur(ID, e));
				}else{
					return new ResponseEntity("Usted no tiene permisos para modificar este perfil",HttpStatus.UNAUTHORIZED);
				}
			}
			/**
			 * Si esta aca es por q es admin o superAdmin y no necesito chequear a quien modifica.
			 */
			return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.editEntreprenur(ID, e));

		} else {
			return new ResponseEntity("No existe el emprendedor con el ID: " + ID, HttpStatus.OK);
		}
	}
}
