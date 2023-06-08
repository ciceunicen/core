package com.project.controller;

import com.project.DTO.DTOAction;
import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
import com.project.entities.Activity;
import com.project.repository.ActionRepository;
import com.project.repository.ActivityRepository;
import com.project.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actividades")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


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
}
