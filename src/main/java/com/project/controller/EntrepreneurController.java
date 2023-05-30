package com.project.controller;

import com.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entities.Entrepreneur;
import com.project.service.implementation.EntrepreneurServiceImp;

import java.util.Optional;

@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {

	@Autowired
	EntrepreneurServiceImp entrepreneurService;
	
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
		if (entrepreneurService.existeID(ID)) {
			return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.editEntreprenur(ID, e));
		} else {
			return new ResponseEntity("No existe el emprendedor con el ID: " + ID, HttpStatus.OK);
		}
	}
}
