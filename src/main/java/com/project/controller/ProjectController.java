package com.project.controller;

import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectUpdate;
import com.project.Mapper.Mapper;
import com.project.entities.*;
import com.project.service.implementation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//ordenamientos
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private StageServiceImp stageServiceImp;
    @Autowired
    private AssistanceServiceImp assistanceServiceImp;
    @Autowired
    private NeedServiceImp needServiceImp;
    @Autowired
    private FileServiceImp fileServiceImp;
    @Autowired
    private ProjectManagerServiceImp projectManagerServiceImp;
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
        return ProjectService.addProject(mapper.toProject(project),project.getStage(),project.getAssistanceType(),project.getNeeds(),project.getId_ProjectManager());
    }

    /**
     * obtiene un proyecto por id
     * @param id es el id del proyecto a buscar
     * @return retorna un proyecto específico, en caso de no encontrarlo retorna error 404
     */
    @GetMapping("/{id_project}")
    public ResponseEntity<?> getProjectById(@PathVariable ("id_project") Long id) {
        Optional <Project>p= ProjectService.getProjectById(id);
        if(!p.isEmpty()) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
        }
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
    	 Integer cantProjects = 15;
    	 //Atributo por el cual se ordena
    	 String sortAttribute = "title";
    	 Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
         return ProjectService.getAll(pageable);
     }

    /**
     * Obtiene todos los proyectos que cumplen con ciertos filtros, estos los devuelve de forma paginada
     * @param datos es un array donde llegan los filtros a aplicar.
     * @param page es un Integer que representa la página a la que apunta.
     * @return retorna Page<Project> una lista de proyectos limitados.
     */
     @GetMapping(value = "/filters/page/{page}",params="filters")
    public Page<Project> getAllProjectsByFiler(@PathVariable("page") Integer page,@RequestParam(value = "filters") List<String> datos ){
         Integer indexPage = page - 1;
         Integer cantProjects = 15;
         String sortAttribute = "title";
         Pageable pageable = PageRequest.of(indexPage, cantProjects, Sort.by(sortAttribute));
         return ProjectService.getAllByFilters(datos,pageable);
     }
     /**
      * Elimina de forma lógica un projecto dado. No se elimina el registro del proyecto en la base de datos, solo se crea un registro en latabla de proyectos eliminados que apunta al proyecto dado.
      * @param id_project de tipo Long, es el ID del proyecto a tratar.
      * @param id_admin de tipo Long es el ID del administrador/a que realizó el borrado del proyecto dado.
      * @return DeletedProject Objeto que representa el registro de la tabla de proyectos eliminados.
      */
    @DeleteMapping("/idProject/{id_project}/idAdmin/{id_admin}")
    public ResponseEntity<?> deleteProject(@PathVariable("id_project") Long id_project, @PathVariable("id_admin") Long id_admin) {
    	 Project response = ProjectService.deleteProject(id_project, id_admin);
         if(response != null) {
             return new ResponseEntity<>(response, HttpStatus.OK);
         }
         return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
    }

    /**
     * Obtiene todos los proyectos eliminados, estos los devuelve de forma paginada.
     * @param page es un Integer que representa la página a la que apunta.
     * @return retorna Page<DeletedProject> una lista de proyectos eliminados limitada.
     */
    @GetMapping("removed/page/{page}")
    public Page<DeletedProject> getAllProjectsRemoved(@PathVariable ("page") Integer page){
        Integer indexPage = page - 1;
        Integer cantProjects = 15;
        String sortAttribute = "title";
        Pageable pageable = PageRequest.of(indexPage, cantProjects,Sort.by(sortAttribute));
        return ProjectService.getAllRemoved(pageable);
    }

    /**
     * Sobreescribe todo el proyecto en la base de datos
     * @param id es el id del proyecto a buscar
     * @param project son los datos de un proyecto a modificar
     * @return un projecto modificado
     */
    @PutMapping("/{id_project}")
    public ResponseEntity<?> updateProject(@PathVariable ("id_project") Long id, @RequestBody DTOProjectUpdate project){
        Project updateProject=ProjectService.getProject(id);
        if (updateProject!=null){
            updateProject.setTitle(project.getTitle());
            updateProject.setDescription(project.getDescription());
            List<Need> needs = new ArrayList<>();
            for (Long idNeed:project.getNeeds()) {
                needs.add(needServiceImp.getNeed(idNeed));
            }
            updateProject.setNeeds(needs);
            List<Assistance> assistances = new ArrayList<>();
            for (Long idAssistance:project.getAssistances()) {
                assistances.add(assistanceServiceImp.getAssistance(idAssistance));
            }
            updateProject.setAssistances(assistances);
            updateProject.setStage(stageServiceImp.getStage(project.getStage()));
            List<File> files = new ArrayList<>();
            for (Long idFiles:project.getFiles()) {
                files.add(fileServiceImp.getFile(idFiles));
            }
            if (project.getNewFiles().size()>0){
                for (File file:project.getNewFiles()) {
                    files.add(fileServiceImp.addFile(file));
                }
            }
            updateProject.setFiles(files);
            Project response = ProjectService.save(updateProject);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("404, NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene el historial de un proyecto en particular, este se devuelve de forma paginada.
     * @param page es un Integer que representa la página a la que apunta.
     * @param id es el id del proyecto que se quiere obtener su historial
     * @return retorna Page<AdministrationRecords> una lista de el historial del proyecto limitada.
     */
    @GetMapping("/{id_project}/administrationRecords/page/{page}")
    public Page<AdministrationRecords> getProjectHistory(@PathVariable ("id_project") Long id, @PathVariable ("page") Integer page){
        Integer indexPage = page - 1;
        Integer cantProjects = 15;
        String sortAttribute = "project.title";
        Pageable pageable = PageRequest.of(indexPage, cantProjects,Sort.by(sortAttribute));
        return ProjectService.getProjectHistory(pageable,id);
    }

    @DeleteMapping("/removeFiles/idProject/{id_project}")
    public ResponseEntity<?> deleteFile(@PathVariable("id_project") Long id_project) {
        List<File> response = fileServiceImp.deleteFilesByProject(id_project);
        if(response != null) {
            fileServiceImp.deleteFilesByProject(id_project);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
    }
}
