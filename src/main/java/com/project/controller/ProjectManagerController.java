package com.project.controller;

import com.project.DTO.DTOProjectManager;
import com.project.Mapper.Mapper;
import com.project.entities.Project;
import com.project.entities.ProjectManager;
import com.project.service.implementation.ProjectManagerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("projectmanagers")
public class ProjectManagerController {
    @Autowired
    private ProjectManagerServiceImp projectManagerServiceImp;
    private Mapper mapper;

    public ProjectManagerController() {
        this.mapper = new Mapper();
    }

    /**
     * obtiene un project manager por id
     * @param id_ProjectManager es el id del project manager
     * @return retorna un project manager en especifico
     */
    @GetMapping(value = "/{id_ProjectManager}")
    public ProjectManager getProjectManager(@PathVariable(value = "id_ProjectManager") Long id_ProjectManager) {
        return projectManagerServiceImp.getProjectManager(id_ProjectManager);
    }

    /**
     * obtiene una lista de todos los project manager
     * @param page es un Integer que representa la página a la que apunta.
     * @return Page<ProjectManager> una lista de project manager limitado.
     */
    @GetMapping("/page/{page}")
    public Page<ProjectManager> getAll(@PathVariable ("page") Integer page){
        //Seteo el indice page, ya que PageRequest toma desde 0 (cero).
        //Si le mandan 1 lo setoe para que apunte a 0.
        //Así la url queda más funcional. De la página 1 en adelante, no desde la 0.
        Integer indexPage = page - 1;
        //cantidad de objetos por página
        Integer cantProjects = 15;
        //Atributo por el cual se ordena
        String sortAttribute = "surname";
        Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
        return projectManagerServiceImp.getAll(pageable);
    }

    /**
     * obtiene una lista de todos los proyectos de un project manager especifico.
     * @param page es un Integer que representa la página a la que apunta.
     * @param id_ProjectManager es el id del project manager
     * @return Page<ProjectManager> una lista de los proyectos de un project manager limitado.
     */
    @GetMapping("/{id_ProjectManager}/projects/page/{page}")
    public Page<Project> getAll(@PathVariable ("page") Integer page, @PathVariable(value = "id_ProjectManager") Long id_ProjectManager){
        //Seteo el indice page, ya que PageRequest toma desde 0 (cero).
        //Si le mandan 1 lo setoe para que apunte a 0.
        //Así la url queda más funcional. De la página 1 en adelante, no desde la 0.
        Integer indexPage = page - 1;
        //cantidad de objetos por página
        Integer cantProjects = 15;
        //Atributo por el cual se ordena
        String sortAttribute = "title";
        Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
        return projectManagerServiceImp.getAllProjects(pageable,id_ProjectManager);
    }

    /**
     * inserta un nuevo proyect manager a la base de datos
     * @param pm son los datos de un proyect manager a cargar
     * @return retorna un dto del archivo cargado a la base de datos
     */
    @PostMapping()
    public ProjectManager addProject(@Valid @RequestBody DTOProjectManager pm){
        return projectManagerServiceImp.addProjectManager(mapper.toProjectManager(pm));
    }
}
