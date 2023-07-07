package com.project.controller;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOEntrepreneurship;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;

    @Autowired
    private RoleAuthController roleAuthController;

    @GetMapping(value = "/filters", params="filters")
    public ResponseEntity<List<DTOEntrepreneurship>> getAllEntrepreneurshipsByFilters(@RequestParam(value = "filters") List<String> data){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOEntrepreneurship> list = this.entrepreneurshipService.getAllByFilters(data);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping (value = "/{ID}/actividades", params="filters")
    public ResponseEntity<List<DTOActivity>> getCompositeProjectActivitiesByFilters(@PathVariable("ID") Long id, @RequestParam(value = "filters") List<String> data) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOActivity> list = this.entrepreneurshipService.getActivitiesByCompositeProjectIdFiltered(id, data);
            if (list != null) {
                return new ResponseEntity(list, HttpStatus.OK);
            }
            else return new ResponseEntity("No existe el recurso con id: " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

}
