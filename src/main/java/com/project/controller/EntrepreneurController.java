package com.project.controller;

import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurInsert;
import com.project.DTO.DTOEntrepreneurUpdate;
import com.project.DTO.DTOProject;
import com.project.service.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
	public Iterable<DTOEntrepreneur> getEntrepreneurs() {
		return entrepreneurService.getEntrepreneurs();
	}
	
	/**
	 * Obtiene un entrepreneur pod id
	 * @param ID id_entrepreneur
	 * @return 200 Ok, Entrepreneur | 404 Not found
	 */
	@GetMapping("/{ID}")
	public ResponseEntity<DTOEntrepreneur> getEntrepreneur(@PathVariable Long ID) {
		DTOEntrepreneur dto =  entrepreneurService.getEntrepreneurById(ID);
		if(dto != null) {
			return new ResponseEntity(dto, HttpStatus.OK);
		}
		return new ResponseEntity("No existe emprendedor con id " + ID, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/filters", params="filters")
	public ResponseEntity<List<DTOEntrepreneur>> getAllEntrepreneursByFilter(@RequestParam(value = "filters") List<String> data,@RequestParam(value = "deleted") boolean deleted){
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
			List<DTOEntrepreneur> list = this.entrepreneurService.getAllByFilters(data,deleted);
			return new ResponseEntity(list, HttpStatus.OK);
		}
		else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Si el usuario haciendo el posteo es un usuario defecto, se asocia su id de usuario al emprendedor
	 * Si es admin o superadmin se deja en null
	 *
	 * Un emprendedor NO puede crear un emprendedor
	 *
	 * @param e nuevo emprendedor
	 * @return nuevo emprendedor y confirmación de creación
	 */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DTOEntrepreneur> postEntrepreneur(@RequestBody DTOEntrepreneurInsert e) {
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
			DTOEntrepreneur dto = entrepreneurService.postEntrepreneur(e, null);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		if (roleAuthController.hasPermission(4)) {
			DTOEntrepreneur dto = entrepreneurService.postEntrepreneur(e, roleAuthController.getCurrentUserId());
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/{ID}/validado")
	public ResponseEntity<?> validateEntrepreneur(@PathVariable Long ID){
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
			DTOEntrepreneur dto =  entrepreneurService.getEntrepreneurById(ID);
			if (dto != null) {
				if (entrepreneurService.setActive(ID)) {
					return new ResponseEntity("Operación realizada con exito",HttpStatus.OK);
				}
			}
			return new ResponseEntity("No existe el recurso con id " + ID, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity("No tiene permisos para realizar esta acción",HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/{ID}")
	public ResponseEntity<DTOEntrepreneur> editEntrepreneur(@RequestBody DTOEntrepreneurUpdate e, @PathVariable Long ID) {
		DTOEntrepreneur dto =  entrepreneurService.getEntrepreneurById(ID);
		/**
		 * Valido si existe el ID que quiere modificar
		 */
		if (dto != null) {
			/**
			 * Si es un usuario defecto o emprendedor SOLO puede modificar su propio perfil
			 * Si es admin o superAdmin, puede modificar cualquier perfil
			 * Si no, se arroja msj y un UNAUTHORIZED
			 */
			if (roleAuthController.hasPermission(4) || roleAuthController.hasPermission(3)) {
				if (roleAuthController.checkCurrentContextUserId(dto.getId_user())) {
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
		DTOEntrepreneur dto =  entrepreneurService.getEntrepreneurById(ID);
		if(dto != null) {
			if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
				if (dto.getIs_active()) {
					this.validateEntrepreneur(ID);
					return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.deleteEntrepreneur(ID));
				}
			}
			if (roleAuthController.hasPermission(4)) {
				if (!dto.getIs_active()) {
					if (roleAuthController.checkCurrentContextUserId(dto.getId_user())) {
						return ResponseEntity.status(HttpStatus.OK).body(entrepreneurService.deleteEntrepreneur(ID));
					}
				}
			}
			return new ResponseEntity("No tiene permisos para eliminar este perfil", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity("No existe emprendedor con id: " + ID, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Obtiene todos los proyectos del emprendedor logueado.
	 * @return Retorna una lista de proyectos del emprendedor logueado
	 */
	@GetMapping("/{ID}/mis_proyectos")
	public ResponseEntity<List<DTOProject>> getMyProjects(@PathVariable Long ID) {

		List<DTOProject> myProjects = entrepreneurService.getProjectsByEntrepreneurId(ID);
		return new ResponseEntity<>(myProjects, HttpStatus.OK);
	}

}
