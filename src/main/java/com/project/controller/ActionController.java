package com.project.controller;

import com.project.DTO.DTOActionUpdate;
import com.project.entities.Action;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "/filters", params="filters")
    public ResponseEntity<List<DTOAction>> getAllActionsByFilter(@RequestParam(value = "filters") List<String> data){
        if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
            List<DTOAction> list = this.actionService.getAllByFilters(data);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else return new ResponseEntity("No tiene permisos para realizar esta acción", HttpStatus.UNAUTHORIZED);
    }

     /**
     * No es posible crear una acción independiente, fuera del marco de un emprendimiento (actividad o proyecto compuesto).
     * La acción se crea al asociarla a un emprendimiento (ver en ActivityController y CompositeProjectController,
     * métodos postActivityAction y postCompositeProjectAction, respectivamente).
     *
     * @param
     * @return

    public ResponseEntity<?> postAction(@RequestBody DTOActionInsert a) {
    if (roleAuthController.hasPermission(1) || roleAuthController.hasPermission(2)) {
    DTOAction act = this.actionService.postAction(a);
    return new ResponseEntity<>(act, HttpStatus.CREATED);
    }
    else return new ResponseEntity("No tiene permisos para crear una accion",HttpStatus.UNAUTHORIZED);
    }
     */
}
