package com.project.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.DTONeed;
import com.project.entities.Need;
import com.project.entities.Project;
import com.project.service.implementation.NeedServiceImp;
import com.project.service.implementation.ProjectServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("needs")
public class NeedController {
	@Autowired
    private NeedServiceImp needService;
	@Autowired
    private RoleAuthController roleAuthController;

	public NeedController() {
		super();
	}
	/*
	 * Obtiene todos las necesidades guardadas en la base de datos 
     * @return Iterable<Need>.
     */
	@GetMapping()
    public Iterable<Need> getAllNeeds(){
        return needService.getAllNeeds();
    }
	

	/**
	 * Guarda una entidad Need a la base de datos, siempre y cuando tenga
	 * los permisos necesarios
	 * 
	 * @param need Necesidad que se va a guardar, no debe ser null
	 * @return si se tiene los permisos adecuados, devuelve la 
	 * necesidad guardada, de lo contrario, error 401 UNAUTHORIZED
	 */
	@PostMapping()
	public ResponseEntity<?> postNeeds(@RequestBody DTONeed need) {
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2) || roleAuthController.hasPermission(3)) {
			Need saveNeed = needService.postNeed(need);
			if(saveNeed != null) {
				return new ResponseEntity<>(saveNeed, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
			}			
		}
		return new ResponseEntity<>("No tiene permisos para crear un nuevo recurso", HttpStatus.UNAUTHORIZED);
	}
	
}
