package com.project.service;

import com.project.entities.AdministrationRecords;
import com.project.entities.DeletedProject;
import com.project.entities.Project;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
//paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Component
public interface ProjectService {

    public Project addProject(Project project,Long id_stage,List<Long> id_assitances,List<Long> id_needs,Long id_ProjectManager);

    public Optional<Project> getProjectById(Long id);
    
    public Page<Project> getAll(Pageable pageable);

    public Page<Project> getAllByFilters(List<String> filters, Pageable pageable);

	public Project deleteProject(Long id_project, Long id_admin);

    public Page<DeletedProject> getAllRemoved(Pageable pageable);

    Page<AdministrationRecords> getProjectHistory(Pageable pageable, Long id);
}
