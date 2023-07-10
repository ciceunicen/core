package com.project.controller;

import com.project.DTO.DTOEntrepreneurship;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprendimientos")
public class EntrepreneurshipController {

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;
    @Autowired
    private RoleAuthController roleAuthController;


    @GetMapping
    public ResponseEntity<List<DTOEntrepreneurship>> getAllEntrepreneurships() {
        if (roleAuthController.hasPermission(1)) {
            List<DTOEntrepreneurship> list = this.entrepreneurshipService.getAllEntrepreneurships();
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<DTOEntrepreneurship> getEntrepreneurship(@PathVariable("ID") Long id){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOEntrepreneurship dto = this.entrepreneurshipService.getEntrepreneurship(id);
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(params="filters")
    public ResponseEntity<List<DTOEntrepreneurship>> getAllEntrepreneurshipsByFilters(@RequestParam(value = "filters") List<String> data){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOEntrepreneurship> list = this.entrepreneurshipService.getAllByFilters(data);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

}
