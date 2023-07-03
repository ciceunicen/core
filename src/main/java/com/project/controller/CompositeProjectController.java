package com.project.controller;

import com.project.DTO.DTOCompositeProject;
import com.project.DTO.DTOCompositeProjectInsert;
import com.project.DTO.DTOCompositeProjectUpdate;
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

    @PostMapping("/{ID}/subemprendimientos/{id}")
    public ResponseEntity<DTOCompositeProject> addCompositeProjectEntrepreneurship(@PathVariable ("ID") Long IDMainProyect, @PathVariable ("id") Long idSubproject) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            Entrepreneurship mainProject = this.compositeProjectService.getCompositeProjectEntity(IDMainProyect);
            if(mainProject != null) {
                Entrepreneurship subProject = this.compositeProjectService.getCompositeProjectEntity(idSubproject);

                if (subProject == null) {
                    subProject = this.activityService.getActivityEntity(idSubproject);
                }
                 if (subProject == null){
                     return new ResponseEntity("No existe el recurso a asociar id " + idSubproject, HttpStatus.NOT_FOUND);
                }
                if (this.compositeProjectService.containsEntrepreneurship(mainProject,subProject) || this.compositeProjectService.containsEntrepreneurship(subProject,mainProject)){
                    return new ResponseEntity("El recurso ya está asociado", HttpStatus.BAD_REQUEST);
                }else {
                    DTOCompositeProject response = this.compositeProjectService.addEntrepreneurship(IDMainProyect, subProject);
                    return new ResponseEntity(response, HttpStatus.OK);
                }
            }
            else return new ResponseEntity("No existe el recurso a asociar id " + IDMainProyect, HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<DTOCompositeProject> updateCompositeProject(@PathVariable ("ID") Long id, @RequestBody DTOCompositeProjectUpdate dto) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOCompositeProject response = this.compositeProjectService.updateCompositeProject(id, dto);
            if (response != null) {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else return new ResponseEntity("No existe el recurso a modificar, id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("No tiene permisos para modificar el recurso",HttpStatus.UNAUTHORIZED);
    }
}
