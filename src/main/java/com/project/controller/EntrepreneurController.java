package com.project.controller;

import com.project.service.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.entities.Entrepreneur;
import java.util.Optional;


@RestController
@RequestMapping("emprendedores")
public class EntrepreneurController {

	@Autowired
	private EntrepreneurService entrepreneurService;
	@Autowired
	private RoleAuthController roleAuthController;

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

	/**
	 * Si el usuario haciendo el posteo es un usuario defecto, se asocia su id de usuario al emprendedor
	 * En otro caso (admin, superadmin, emprendedor) se deja en null
	 * @param e nuevo emprendedor
	 * @return nuevo emprendedor y confirmación de creación
	 */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Entrepreneur postEntrepreneur(@RequestBody Entrepreneur e) {
		if (roleAuthController.hasPermission(4)) {
			return entrepreneurService.postEntrepreneur(e, roleAuthController.getCurrentUserId());
		} else {
			return entrepreneurService.postEntrepreneur(e, null);
		}
	}

	@PutMapping("/{ID}/validado")
	public ResponseEntity<?> validateEntrepreneur(@PathVariable Long ID){
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
			if (entrepreneurService.setActive(ID)) {
				return new ResponseEntity("Operación realizada con exito",HttpStatus.OK);
			}
			else return new ResponseEntity("No existe emprendedor con id " + ID, HttpStatus.NOT_FOUND);
		}
		else return new ResponseEntity("No tiene permisos para realizar esta acción",HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/{ID}")
	public ResponseEntity<Entrepreneur> editEntrepreneur(@RequestBody Entrepreneur e, @PathVariable Long ID) {
		Optional<Entrepreneur> o =  entrepreneurService.getEntrepreneurById(ID);
		/**
		 * Valido si existe el ID que quiere modificar
		 */
		if (o.isPresent()) {
			/**
			 * Si es un usuario defecto o emprendedor SOLO puede modificar su propio perfil
			 * Si es admin o superAdmin, puede modificar cualquier perfil
			 * Si no, se arroja msj y un UNAUTHORIZED
			 */
			if (roleAuthController.hasPermission(4) || roleAuthController.hasPermission(3)) {
				if (roleAuthController.checkCurrentContextUserId(o.get().getId_user())) {
					return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.editEntrepreneur(ID, e, true));
				}
			}
			if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
				return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.editEntrepreneur(ID, e, false));
			}
			else return new ResponseEntity("No tiene permisos para modificar este perfil",HttpStatus.UNAUTHORIZED);

		} else {
			return new ResponseEntity("No existe el emprendedor con ID: " + ID, HttpStatus.OK);
		}
	}

	/**
	 * Borrado logico de un emprendedor, setea is_deleted = true
	 *
	 * Admin o superadmin SIEMPRE pueden borrar cualquier emprendedor
	 * El usuario defecto sólo puede borrar su propio perfil mientras NO esté activo
	 * Un emprendedr no puede borrar un perfil (que sea emprendedor implica que su perfil está activo)
	 *
	 * @param ID id del emprendedor a borrar
	 * @return si el emprendedor existe devuelve el emprendedor modificado (estuviera eliminado o no) sino devuelve un NOT_FOUND
	 */
	@DeleteMapping("/{ID}")
	public ResponseEntity<?> deleteEntrepreneur(@PathVariable Long ID) {
		Optional<Entrepreneur> o =  entrepreneurService.getEntrepreneurById(ID);
		if(o.isPresent()) {
			if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
				return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.deleteEntrepreneur(ID).get());
			}
			if (roleAuthController.hasPermission(4)) {
				if (!o.get().getIs_active()) {
					if (roleAuthController.checkCurrentContextUserId(o.get().getId_user())) {
						return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.deleteEntrepreneur(ID).get());
					}
				}
			}
			return new ResponseEntity("No tiene permisos para eliminar este perfil", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>("No existe emprendedor con id: " + ID, HttpStatus.NOT_FOUND);
		}
	}

}
