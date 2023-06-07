package com.project.controller;

import com.project.DTO.DTOAction;
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
    private UserService userService;

    @PostMapping()
    public Action postAction(@RequestBody DTOAction a) {
        return this.actionService.postAction(a);
    }


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

    @PutMapping("/{ID}")
    public ResponseEntity<DTOActionUpdate> updateAction(@PathVariable ("ID") Long id, @RequestBody DTOActionUpdate action){
        Optional<Action> act =  actionService.getActionById(id);
        if(!act.isEmpty()) {
            /**
             * Reviso si tiene permisos para editar (Solo un Administrador o un superAdmin puede editar)
             */
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User usuario = userService.findById(u.getId());
            if (usuario.getRole().getType().toLowerCase().equals("admin") || usuario.getRole().getType().toLowerCase().equals("superadmin")){
                return ResponseEntity.status(HttpStatus.OK).body(actionService.updateAction(id, action));
            }
            return new ResponseEntity("Usted no tiene permisos para modificar este perfil",HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity("No existe la accion a modificar con id " + id, HttpStatus.NOT_FOUND);
    }
}
