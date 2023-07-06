package com.project.controller;


import com.project.DTO.DTOActivity;
import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurship;
import com.project.entities.Entrepreneurship;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
