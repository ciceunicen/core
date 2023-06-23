package com.project.controller;

import com.project.DTO.DTOComposedProject;
import com.project.DTO.DTOComposedProjectInsert;
import com.project.DTO.DTOEntrepreneurship;
import com.project.DTO.DTOEntrepreneurshipInsert;
import com.project.entities.Entrepreneurship;
import com.project.service.ComposedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprendimientos")
public class ComposedProjectController {

    @Autowired
    private ComposedProjectService composedProjectService;
    @Autowired
    private RoleAuthController roleAuthController;

    @PostMapping
    public ResponseEntity<DTOComposedProject> postComposedProject(@RequestBody DTOComposedProjectInsert cp) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOComposedProject dto = composedProjectService.postComposedProject(cp);
            if(dto != null) return new ResponseEntity(dto, HttpStatus.CREATED);
            else return new ResponseEntity("Recurso inexistente o ya asociado", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{id}/emprendimientos")
    public ResponseEntity<DTOComposedProject> postComposedProjectEntrepreneurship(@RequestBody Entrepreneurship e, @PathVariable Long id) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOComposedProject dto = composedProjectService.addEntrepreneurship(e, id);
            if(dto != null) {
                return new ResponseEntity(dto, HttpStatus.CREATED);
            }
            else return new ResponseEntity("No existe el recurso id: " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }
}
