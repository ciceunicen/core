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

import java.util.Optional;

@RestController
@RequestMapping("/actividades")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> postActivity(@RequestBody DTOActivityInsert a) {
        DTOActivity dto = this.activityService.postActivity(a);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @PostMapping("{ID}/acciones")
    public ResponseEntity<?> postActivityAction(@RequestBody DTOActionInsert a,@PathVariable Long ID) {
        DTOActivity dto = this.activityService.postActivityAction(a,ID);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getActivity(@PathVariable Long ID){
        DTOActivity dto = this.activityService.getActivity(ID);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>("No existe la actividad con el id: " + ID,HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<DTOActivity> getActivities(){ return this.activityService.getActivities();}

    @PutMapping("/{ID}")
    public ResponseEntity<DTOActivityUpdate> updateAction(@PathVariable ("ID") Long id, @RequestBody DTOActivityUpdate action){
        DTOActivityUpdate act = activityService.getActivityUpdate(id);
        if(act != null) {
            /**
             * Reviso si tiene permisos para editar (Solo un Administrador o un superAdmin puede editar)
             */
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User usuario = userService.findById(u.getId());
            if (usuario.getRole().getType().toLowerCase().equals("admin") || usuario.getRole().getType().toLowerCase().equals("superadmin")){
                return ResponseEntity.status(HttpStatus.OK).body(activityService.updateActivity(id, act));
            }
            return new ResponseEntity("Usted no tiene permisos para modificar esta actividad",HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity("No existe la actividad a modificar con id " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<DTOActivity> deleteAction(@PathVariable ("ID") Long id){
        DTOActivity act =  activityService.getActivity(id);
        if(act != null) {
            /**
             * Reviso si tiene permisos para eliminar (Solo un Administrador o un superAdmin puede eliminar)
             */
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User usuario = userService.findById(u.getId());
            if (usuario.getRole().getType().toLowerCase().equals("admin") || usuario.getRole().getType().toLowerCase().equals("superadmin")){
                return ResponseEntity.status(HttpStatus.OK).body(activityService.deleteActivity(id));
            }
            return new ResponseEntity("Usted no tiene permisos para eliminar esta actividad",HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity("No existe la actividad  a eliminar con id " + id, HttpStatus.NOT_FOUND);
    }

}
