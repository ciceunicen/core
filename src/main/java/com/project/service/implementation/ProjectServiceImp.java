package com.project.service.implementation;

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
        return projectRepository.save(project);
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
		return projectRepository.findAll(pageable);
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
}
