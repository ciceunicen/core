package com.project.controller;

import com.project.DTO.DTOCompositeProject;
import com.project.DTO.DTOCompositeProjectInsert;
import com.project.entities.Activity;
import com.project.entities.CompositeProject;
import com.project.entities.Entrepreneurship;
import com.project.service.ActivityService;
import com.project.service.CompositeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprendimientos")
public class CompositeProjectController {

    @Autowired
    private CompositeProjectService compositeProjectService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private RoleAuthController roleAuthController;

    @GetMapping
    public ResponseEntity<Iterable<DTOCompositeProject>> getCompositeProjects() {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            Iterable<DTOCompositeProject> dtos = this.compositeProjectService.getCompositeProjects();
            return new ResponseEntity(dtos, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping ("/{ID}")
    public ResponseEntity<DTOCompositeProject> getCompositeProject(@PathVariable Long ID) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOCompositeProject dto = this.compositeProjectService.getCompositeProject(ID);
            if (dto != null) return new ResponseEntity(dto, HttpStatus.OK);
            else return new ResponseEntity("No existe el recurso con id: " + ID, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<DTOCompositeProject> postCompositeProject(@RequestBody DTOCompositeProjectInsert cp) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOCompositeProject dto = compositeProjectService.postCompositeProject(cp);
            if(dto != null) return new ResponseEntity(dto, HttpStatus.CREATED);
            else return new ResponseEntity("No se pudo crear el recurso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{ID}/emprendimientos/{id}")
    public ResponseEntity<DTOCompositeProject> addCompositeProjectEntrepreneurship(@PathVariable Long ID, @PathVariable Long id) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            //No puede asociarse a sí mismo...
            if (ID != id) {
                DTOCompositeProject dto = this.compositeProjectService.getCompositeProject(ID);
                if(dto != null) {
                    /*----------------------------------------*/
                    Entrepreneurship e = this.compositeProjectService.getCompositeProjectEntity(id);
                    if (e != null) {
                        //Un hijo no puede contener a su padre...
                        if (this.compositeProjectService.containsEntrepreneurship(id, ID)) {
                                //TO DO: Puede asociar un hermano??
                            return new ResponseEntity("El recurso ya está asociado", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        e = this.activityService.getActivityEntity(id);
                    }

                    if (e != null) {
                        //No puede asociar dos veces el mismo hijo...
                        if (this.compositeProjectService.containsEntrepreneurship(ID, id)) {
                            DTOCompositeProject response = this.compositeProjectService.addEntrepreneurship(ID, e);
                            return new ResponseEntity(response, HttpStatus.OK);
                        }
                        return new ResponseEntity("El recurso ya está asociado", HttpStatus.BAD_REQUEST);
                    }
                    else return new ResponseEntity("No existe el recurso a asociar id " + id, HttpStatus.NOT_FOUND);

                }
                else return new ResponseEntity("No existe el recurso id " + ID, HttpStatus.NOT_FOUND);

            }
            else return new ResponseEntity("Un recurso no puede asociarse a sí mismo", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

}
