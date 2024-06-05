package com.project.controller;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionUpdate;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("acciones")
public class ActionController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private RoleAuthController roleAuthController;

    @GetMapping()
    public ResponseEntity<DTOAction> getActions(){
        List<DTOAction> list = this.actionService.getActions();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getAction(@PathVariable Long ID ){
        DTOAction act =  actionService.getActionById(ID);
        if(act != null) {
            return new ResponseEntity<>(act, HttpStatus.OK);
        }
        return new ResponseEntity<>("No existe accion con id " + ID, HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="filters")
    public ResponseEntity<List<DTOAction>> getAllActionsByFilter(@RequestParam(value = "filters") List<String> data){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOAction> list = this.actionService.getAllByFilters(data);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<DTOActionUpdate> updateAction(@PathVariable ("ID") Long id, @RequestBody DTOActionUpdate action){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            DTOAction act =  actionService.getActionById(id);
            if(act != null) {
                return ResponseEntity.status(HttpStatus.OK).body(actionService.updateAction(id, action));
            }
            else return new ResponseEntity("No existe la accion id " + id, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity("No tiene permisos para modificar una accion",HttpStatus.UNAUTHORIZED);
    }

}
