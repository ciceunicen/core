package com.project.controller;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActionUpdate;
import com.project.DTO.DTOProjectUpdate;
import com.project.entities.Action;
import com.project.entities.Entrepreneur;
import com.project.entities.User;
import com.project.service.ActionService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("acciones")
public class ActionController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private RoleAuthController roleAuthController;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Action> getActions(){
        return this.actionService.getActions();
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getAction(@PathVariable Long ID ){
        Optional<Action> act =  actionService.getActionById(ID);
        if(!act.isEmpty()) {
            return new ResponseEntity<>(act, HttpStatus.OK);
        }
        return new ResponseEntity<>("No existe accion con id " + ID, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> postAction(@RequestBody DTOActionInsert a) {
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOAction act = this.actionService.postAction(a);
            return new ResponseEntity<>(act, HttpStatus.CREATED);
        }
        else return new ResponseEntity("No tiene permisos para crear una accion",HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<DTOActionUpdate> updateAction(@PathVariable ("ID") Long id, @RequestBody DTOActionUpdate action){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            Optional<Action> act =  actionService.getActionById(id);
            if(!act.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(actionService.updateAction(id, action));
            }
            else return new ResponseEntity("No existe la accion id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para modificar una accion",HttpStatus.UNAUTHORIZED);
    }


    @DeleteMapping("/{ID}")
    public ResponseEntity<Action> deleteAction(@PathVariable ("ID") Long id){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            Optional<Action> act =  actionService.getActionById(id);
            if(!act.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(actionService.deleteAction(id));
            }
            else return new ResponseEntity("No existe la accion id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("Noo tiene permisos para eliminar una accion",HttpStatus.UNAUTHORIZED);
    }
}
