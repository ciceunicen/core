package com.project.controller;

import com.project.DTO.*;
import com.project.entities.Action;
import com.project.entities.Activity;
import com.project.entities.User;
import com.project.repository.ActionRepository;
import com.project.repository.ActivityRepository;
import com.project.service.ActivityService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actividades")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private RoleAuthController roleAuthController;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<DTOActivity> getActivities(){ return this.activityService.getActivities();}

    @GetMapping("/{ID}")
    public ResponseEntity<?> getActivity(@PathVariable Long ID){
        DTOActivity dto = this.activityService.getActivity(ID);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        else return new ResponseEntity<>("No existe la actividad con el id: " + ID,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> postActivity(@RequestBody DTOActivityInsert a) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOActivity dto = this.activityService.postActivity(a);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        else return new ResponseEntity("No tiene permisos para crear una actividad",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("{ID}/acciones")
    public ResponseEntity<?> postActivityAction(@RequestBody DTOActionInsert a,@PathVariable Long ID) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOActivity dto = this.activityService.postActivityAction(a, ID);
            if (dto != null) {
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }
            else return new ResponseEntity<>("No existe el recurso a modificar, id " + ID, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para agregar una acción a una actividad",HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<DTOActivityUpdate> updateActivity(@PathVariable ("ID") Long id, @RequestBody DTOActivityUpdate activity){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOActivityUpdate act = activityService.getActivityUpdate(id);
            if(act != null) {
                return ResponseEntity.status(HttpStatus.OK).body(activityService.updateActivity(id, activity));
            }
            else return new ResponseEntity("No existe la actividad id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para modificar una actividad",HttpStatus.UNAUTHORIZED);
    }

    /*
        Borrado fisico de una Actividad
     */
    @DeleteMapping("/{ID}")
    public ResponseEntity<DTOActivity> deleteActivity(@PathVariable ("ID") Long id){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOActivity act =  activityService.getActivity(id);
            if(act != null) {
                return ResponseEntity.status(HttpStatus.OK).body(activityService.deleteActivity(id));
            }
            else return new ResponseEntity("No existe la actividad id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para eliminar una actividad",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/filters", params="filters")
    public ResponseEntity<List<DTOActivity>> getAllActivityByFilter(@RequestParam(value = "filters") List<String> data){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOActivity> list = this.activityService.getAllByFilters(data);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }
}
