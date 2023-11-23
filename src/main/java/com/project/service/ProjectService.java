package com.project.service;

import com.project.DTO.DTOActionInsert;
import com.project.DTO.DTOProject;
import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectUpdate;
import com.project.entities.AdministrationRecords;
import com.project.entities.DeletedProject;
import com.project.entities.Entrepreneurship;
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

    public Project getProjectEntity(Long id);
    
    public DTOProject postProject(DTOProjectInsert cp);
    
    public Iterable<DTOProject> getProjects();

    public DTOProject getProject(Long id);

    public List<DTOProject> getProjectsThatContain(Long id);

    public DTOProject addEntrepreneurship(Long main_project_id, Entrepreneurship e);

    public boolean containsEntrepreneurship(Entrepreneurship mainProject,Entrepreneurship subProject);

    public boolean containsCommonEntrepreneurships(Long main_project_id, Long subproject_id);

    public DTOProject postProjectAction(DTOActionInsert a, Long id);
}
