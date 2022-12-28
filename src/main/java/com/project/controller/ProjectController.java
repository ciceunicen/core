package com.project.controller;

import com.project.DTO.DTOProjectFilter;
import com.project.DTO.DTOProjectInsert;
import com.project.Mapper.Mapper;
import com.project.entities.Project;
import com.project.service.implementation.ProjectServiceImp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//ordenamientos
import org.springframework.data.domain.Sort;


import java.util.Optional;

import javax.validation.Valid;
/**
 * 
 * @author Colaborativo
 *
 */
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
    public Optional<Project> getProjectById(@PathVariable ("id_project") Long id){
        return ProjectService.getProjectById(id);
    }
     /**
      * Obtiene todos los proyectos guardados en la base de datos, estos los devuelve de forma paginada.
      * @param page es un Integer que representa la página a la que apunta. 
      * @return retorna Page<Project> una lista de proyectos limitado.
      */
     @GetMapping("/page/{page}")
     public Page<Project> getAllProjects(@PathVariable ("page") Integer page){
    	 //Seteo el indice page, ya que PageRequest toma desde 0 (cero).
    	 //Si le mandan 1 lo setoe para que apunte a 0.
    	 //Así la url queda más funcional. De la página 1 en adelante, no desde la 0.
    	 Integer indexPage = page - 1;
    	 //cantidad de objetos por página
    	 Integer cantProjects = 25;
    	 //Atributo por el cual se ordena
    	 String sortAttribute = "title";
    	 Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
         return ProjectService.getAll(pageable);
     }

    /**
     * Obtiene todos los proyectos que cumplen con ciertos filtros, estos los devuelve de forma paginada
     * @param filter es un DTO donde llegan los filtros a aplicar y la pagina a la que apunta.
     * @param page es un Integer que representa la página a la que apunta.
     * @return retorna Page<Project> una lista de proyectos limitados.
     */
     @GetMapping("/filters/page/{page}")
    public Page<Project> getAllProjectsByFiler(@PathVariable ("page") Integer page,@Valid @RequestBody DTOProjectFilter filter){
         Integer indexPage = page - 1;
         Integer cantProjects = 25;
         String sortAttribute = "title";
         Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
         return ProjectService.getAllByFilters(filter.getFilters(),pageable);


     }
    
}
