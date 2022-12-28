package com.project.service.implementation;

import com.project.entities.Project;
import com.project.repository.ProjectRepository;
import com.project.service.ProjectService;

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

    @Override
    public Project addProject(Project project, Long id_ProjectManager) {
        project.setProjectManager(projectRepository.getProjectManager(id_ProjectManager));
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
    
    
}
