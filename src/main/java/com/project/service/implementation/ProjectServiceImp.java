package com.project.service.implementation;

import com.project.entities.AdministrationRecords;
import com.project.entities.DeletedProject;
import com.project.entities.Project;
import com.project.repository.*;
import com.project.service.ProjectService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 
 * @author Colaborativo
 *
 */
@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectManagerRepository projectManagerRepository;
    @Autowired
    private NeedRepository needRepository;
    @Autowired
    private AssistanceRepository assistanceRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private DeletedProjectRepository deletedProjetcRepository;
    @Autowired
    private AdministrationRecordsRepository administrationRecordsRepository;

    @Override
    public Project addProject(Project project,Long id_stage,Long[] id_assitances,Long[] id_needs, Long id_ProjectManager) {
        project.setProjectManager(projectManagerRepository.getByProjectManagerById(id_ProjectManager));

        for (Long id:id_needs) {
            project.addNeed(needRepository.getNeed(id));
        }
        for (Long id:id_assitances) {
            project.addAssistance(assistanceRepository.getAssistance(id));
        }
        project.setStage(stageRepository.getStage(id_stage));
        project = projectRepository.save(project);
        AdministrationRecords ar=new AdministrationRecords(project,"creación de proyecto");
        administrationRecordsRepository.save(ar);
        return project;
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    /**
     * Consulta a projectRepository por todos los proyectos cargados en la base de datos, estos paginados.
     * @param pageable es un objeto de tipo Pageable, que indica el indice de página y la cantidad de objetos por página.
     * @return retorna Page<Project> una lista de proyectos, esta lista se encuentra limitada.
     */
	@Override
	public Page<Project> getAll(Pageable pageable) {
		return projectRepository.getAll(pageable);
	}

    /**
     * consulta a projectRepository por todos los proyectos que cumplen con determinados filtros, estos paginados
     * @param filters lista de filtros por los cuales se consultara a la base de datos
     * @param pageable es un objeto de tipo Pageable, que indica el indice de página y la cantidad de objetos por página.
     * @return retorna Page<Project> una lista de proyectos que cumplen con determinados filtros, esta lista se encuentra limitada.
     */
    @Override
    public Page<Project> getAllByFilters(List<String> filters,Pageable pageable) {
        return projectRepository.findAll(filters,pageable);
    }
    
    /**
     * Realiza eliminado lógico de la base de datos, chequea que el proyecto exist y que ya no haya sido eliminado.
     * @param id_project es el ID del proyecto a eliminar
     * @param id_admin es el ID del administrador que realiza esta eliminación
     * @return retorna el proyecto elminado o null si ya se encuentra eliminado.
     */
    @Override
	public Project deleteProject(Long id_project, Long id_admin) {
    	Optional <Project> project = this.getProjectById(id_project);
    	Boolean isRemoved = (deletedProjetcRepository.getDeletedProjectByIdProject(id_project) != null);
    	if(!project.isEmpty() && !isRemoved) {
    		DeletedProject deleteProject = new DeletedProject();
    		deleteProject.setProject(project.get());
    		deleteProject.setId_admin(id_admin);
    		return deletedProjetcRepository.save(deleteProject).getProject();
    	}
		return null;
	}

    /**
     * Consulta a deletedProjetcRepository por todos los proyectos eliminados, estos paginados.
     * @param pageable es un objeto de tipo Pageable, que indica el indice de página y la cantidad de objetos por página.
     * @return retorna Page<DeletedProject> una lista de proyectos eliminados, esta lista se encuentra limitada.
     */
    @Override
    public Page<DeletedProject> getAllRemoved(Pageable pageable) {
        return deletedProjetcRepository.findAll(pageable);
    }

    /**
     * guarda un proyecto editado
     * @param p es un proyecto modificado
     * @return retorna el proyecto cargado
     */
    public Project save(Project p){
        p=projectRepository.save(p);
        AdministrationRecords ar=new AdministrationRecords(p,"Modificación de proyecto");
        administrationRecordsRepository.save(ar);
        return p;
    }

    /**
     * Consulta a administrationRecordsRepository por todo el historial de un proyecto, este historial se retorna paginado.
     * @param pageable es un objeto de tipo Pageable, que indica el indice de página y la cantidad de objetos por página.
     * @param id es el id del proyecto que se quiere consultar el historial
     * @return retorna Page<AdministrationRecords> una lista de el historial del proyecto, esta lista se encuentra limitada.
     */
    @Override
    public Page<AdministrationRecords> getProjectHistory(Pageable pageable, Long id) {
        return administrationRecordsRepository.getProjectHistory(pageable,id);
    }
}
