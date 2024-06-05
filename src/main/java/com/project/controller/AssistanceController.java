package com.project.controller;

import com.project.entities.Assistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.DTOAssistance;
import com.project.service.implementation.AssistanceServiceImp;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("assistances")
public class AssistanceController {
	
	@Autowired
    private AssistanceServiceImp assistanceService;
	@Autowired
    private RoleAuthController roleAuthController;

	public AssistanceController() {
		super();
	}
	
	/*
	 * Obtiene todos las asistencias guardadas en la base de datos 
     * @return Iterable<Assistance>.
     */
	@GetMapping()
    public Iterable<Assistance> getAllAssitances(){
        return assistanceService.getAllAssistances();
    }
	
	/**
	 * Guarda una entidad Assistance a la base de datos, siempre y cuando tenga
	 * los permisos necesarios
	 * 
	 * @param assistance Asistencia que se va a guardar, no debe ser null
	 * @return si se tiene los permisos adecuados, devuelve la 
	 * asistencia guardada, de lo contrario, error 401 UNAUTHORIZED
	 */
	@PostMapping()
	public ResponseEntity<?> postAssistance(@RequestBody DTOAssistance assistance) {
		if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2) || roleAuthController.hasPermission(3)) {
			Assistance saveAssistance = assistanceService.postAssistance(assistance);
			if(saveAssistance != null) {
				return new ResponseEntity<>(saveAssistance, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
			}			
		}
		return new ResponseEntity<>("No tiene permisos para crear un nuevo recurso", HttpStatus.UNAUTHORIZED);
	}
	

}
