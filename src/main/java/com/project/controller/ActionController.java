package com.project.controller;

import com.project.DTO.DTOAction;
import com.project.entities.Action;
import com.project.entities.Entrepreneur;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("acciones")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping()
    public Action postAction(@RequestBody DTOAction a) {
        return this.actionService.postAction(a);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getAction(@PathVariable Long ID ){
        Optional<Action> act =  actionService.getActionById(ID);
        if(!act.isEmpty()) {
            return new ResponseEntity<>(act, HttpStatus.OK);
        }
        return new ResponseEntity<>("No existe accion con id " + ID, HttpStatus.NOT_FOUND);
    }

}
