package com.project.controller;

import com.project.entities.ProjectManager;
import com.project.service.implementation.ProjectManagerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projectmanagers")
public class ProjectManagerController {
    @Autowired
    private ProjectManagerServiceImp projectManagerServiceImp;


    @GetMapping(value = "/{id_ProjectManager}")
    public ProjectManager getProjectManager(@PathVariable(value = "id_ProjectManager") Long id_ProjectManager) {
        return projectManagerServiceImp.getProjectManager(id_ProjectManager);
    }
}
