package com.project.controller;

import com.project.DTO.DTOProjectInsert;
import com.project.Mapper.Mapper;
import com.project.entities.Project;
import com.project.service.implementation.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@RestController
@RequestMapping("projects")
public class ProjectController {
    @Autowired
    private ProjectServiceImp ProjectService;
    private Mapper mapper;

    public ProjectController() {
        this.mapper = new Mapper();
    }

    /**
     * inserta un nuevo proyecto a la base de datos
     * @param project son los datos de un proyecto a cargar
     * @return retorna un dto del archivo cargado a la base de datos
     */
    @PostMapping()
    public Project addProject(@Valid @RequestBody DTOProjectInsert project){
        return ProjectService.addProject(mapper.toProject(project),project.getId_ProjectManager());
    }

    @GetMapping("/{id_project}")
    public ResponseEntity<?> getProjectById(@PathVariable ("id_project") Long id) {
        Optional <Project>p= ProjectService.getProjectById(id);
        if(!p.isEmpty()) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
   
}
