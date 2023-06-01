package com.project.controller;

import com.project.entities.User;
import com.project.service.EntrepreneurService;
import com.project.service.UserService;
import org.hibernate.hql.internal.classic.AbstractParameterInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.project.entities.Entrepreneur;
import java.util.Optional;


@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {

	@Autowired
	private EntrepreneurService entrepreneurService;

	@Autowired
	private UserService userService;

	/**
	 * Obtiene todos los emprendedores
	 * @return 200 Ok, emprendedores
	 */
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Entrepreneur> getEntrepreneurs() {
		return entrepreneurService.getEntrepreneurs();
	}

	/**
	 * Obtiene un entrepreneur pod id
	 * @param ID id_entrepreneur
	 * @return 200 Ok, Entrepreneur | 404 Not found
	 */
	@GetMapping("/{ID}")
	public ResponseEntity<?> getEntrepreneur(@PathVariable Long ID) {
		Optional<Entrepreneur> o =  entrepreneurService.getEntrepreneurById(ID);
		if(!o.isEmpty()) {
			return new ResponseEntity<>(o, HttpStatus.OK);
		}
		return new ResponseEntity<>("No existe emprendedor con id " + ID, HttpStatus.NOT_FOUND);
	}


	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Entrepreneur postEntrepreneur(@RequestBody Entrepreneur e) {
		return entrepreneurService.postEntrepeneur(e);
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

	@PutMapping("/{ID}")
	public ResponseEntity<Entrepreneur> editEntreprenur(@RequestBody Entrepreneur e,@PathVariable Long ID) {
		/**
		 * Valido si existe el ID que quiere modificar
		 */
		if (entrepreneurService.existeID(ID)) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User usuario = userService.findById(u.getId());
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

	/**
	 * Borrado logico de un emprendedor, setea is_deleted = true
	 * @param ID id del emprendedor a borrar
	 * @return si el emprendedor existe devuelve el emprendedor modificado (estuviera eliminado o no) sino devuelve un NOT_FOUND
	 */
	@DeleteMapping("/{ID}")
	public ResponseEntity<?> deleteEntrepreneur(@PathVariable Long ID) {
		Optional<Entrepreneur> o =  entrepreneurService.deleteEntrepreneur(ID);
		if(!o.isEmpty()) {
			return new ResponseEntity<>(o, HttpStatus.OK);
		}
		return new ResponseEntity<>("No existe un emprendedor con el id: "+ ID, HttpStatus.NOT_FOUND);
	}

}
