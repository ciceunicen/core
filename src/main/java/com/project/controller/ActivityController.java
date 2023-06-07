package com.project.controller;

import com.project.DTO.DTOActivity;
import com.project.DTO.DTOActivityInsert;
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
}
