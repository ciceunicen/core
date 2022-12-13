package com.project.controller;

import com.project.DTO.DTOProjectInsert;
import com.project.Mapper.Mapper;
import com.project.service.implementation.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Project")
public class ProjectController {
    @Autowired
    private ProjectServiceImp ProjectService;
    private Mapper mapper;

    public ProjectController() {
        this.mapper = new Mapper();
    }

    @PostMapping()
    public DTOProjectInsert addProject(@RequestBody DTOProjectInsert project){
        ProjectService.addProject(mapper.toProject(project));
        return project;
    }
}
