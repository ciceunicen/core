package com.project.controller;

import com.project.DTO.*;
import com.project.entities.Action;
import com.project.entities.Entrepreneurship;
import com.project.service.ActivityService;
import com.project.service.CompositeProjectService;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/proyectos")
public class CompositeProjectController {

    @Autowired
    private CompositeProjectService compositeProjectService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EntrepreneurshipService entrepreneurshipService;
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

    @GetMapping (params="subemprendimiento_id")
    public ResponseEntity<List<DTOCompositeProject>> getProjectsThatContains(@RequestParam("subemprendimiento_id") Long id) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOCompositeProject> list = this.compositeProjectService.getCompositeProjectsThatContain(id);
            if(list != null) {
                return new ResponseEntity(list, HttpStatus.OK);
            }
            else return new ResponseEntity("Ha ocurrido un error al realizar la consulta", HttpStatus.INTERNAL_SERVER_ERROR);
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
                 //No chequea en profundidad
                 if (this.compositeProjectService.containsCommonEntrepreneurships(IDMainProyect, idSubproject)) {
                     return new ResponseEntity("Los recursos tienen emprendimientos asociados en común ", HttpStatus.BAD_REQUEST);
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

    /*
      Borrado fisico de un proyecto compuesto
      Siempre que no sea parte de un composite
   */
    @DeleteMapping("/{ID}")
    public ResponseEntity<DTOCompositeProject> deleteCompositeProject(@PathVariable ("ID") Long id){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOCompositeProject dto = compositeProjectService.getCompositeProject(id);
            if(dto != null) {
                if(this.entrepreneurshipService.deleteEntrepreneurship(id)) {
                    return ResponseEntity.status(HttpStatus.OK).body(dto);
                }
                else return new ResponseEntity("No es posible eliminar el recurso id " + id + " ya que está asociado a otros emprendimientos", HttpStatus.UNAUTHORIZED);
            }
            else return new ResponseEntity("No existe la actividad id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para eliminar una actividad",HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("{ID}/acciones")
    public ResponseEntity<?> postCompositeProjectAction(@RequestBody DTOActionInsert a, @PathVariable ("ID") Long id) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOCompositeProject dto = this.compositeProjectService.postCompositeProjectAction(a, id);
            if (dto != null) {
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }
            else return new ResponseEntity<>("No existe el recurso a modificar, id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción",HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping ("/{ID}/acciones/{action_ID}")
    public ResponseEntity<Action> deleteAction(@PathVariable("ID") Long entrepreneurship_id, @PathVariable("action_ID") Long action_id) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOEntrepreneurship e = this.entrepreneurshipService.getEntrepreneurship(entrepreneurship_id);
            if (e != null) {
                Action a = this.entrepreneurshipService.deleteAction(entrepreneurship_id, action_id);
                if (a != null) {
                    return new ResponseEntity("Se ha borrado la acción id: " + action_id, HttpStatus.OK);
                }
                else return new ResponseEntity("No existe la acción id: " + action_id, HttpStatus.NOT_FOUND);
            }
            else return new ResponseEntity("No existe el recurso con id: " + entrepreneurship_id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para crear un nuevo recurso",HttpStatus.UNAUTHORIZED);
    }

}
