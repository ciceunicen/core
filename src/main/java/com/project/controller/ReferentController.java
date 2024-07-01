package com.project.controller;

import com.project.DTO.DTOProjectUpdate;
import com.project.DTO.response.DTOReferent;
import com.project.entities.User;
import com.project.service.ReferentService;
import com.project.service.implementation.ReferentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Referent")
public class ReferentController {
    @Autowired
    private ReferentServiceImp serviceimp;

    @GetMapping("/ID/{IDproject}")
    public ResponseEntity<?> getReferentByIDproject(@PathVariable Long IDproject){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceimp.findByIdproject(IDproject));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    @PutMapping("/IDproject/{id_project}")
    public ResponseEntity<?> UpdateReferent(@RequestBody DTOReferent referentDto,@PathVariable Long id_project){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceimp.UpdateReferent(referentDto,id_project));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

}
