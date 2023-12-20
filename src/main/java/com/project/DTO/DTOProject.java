package com.project.DTO;

import java.util.List;

import com.project.entities.Action;
import com.project.entities.Assistance;
import com.project.entities.Entrepreneurship;
import com.project.entities.File;
import com.project.entities.Need;
import com.project.entities.Project;
import com.project.entities.ProjectManager;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DTOProject {

	private Long id_Project;
	private String title;
	private String description;
	private String stage;
	private Long administrador;

	private boolean is_active;

	private List<Assistance> assistances;
	private ProjectManager projectManager;
	private List<File> files;
	private List<Need> needs;
	private List<Action> actions;
	private List<Entrepreneurship> entrepreneurships;
	private String projectManagerName;
	private String adminUsername;
	private String adminEmail;
	
	public DTOProject(Project project, String adminUsername, String adminEmail) {
		this.id_Project = project.getId_Project();
		this.title = project.getTitle();
		this.description = project.getDescription();
		this.stage = project.getStage().getStage_type();
		this.administrador = project.getAdministrador();
		this.is_active = project.is_active();
		this.assistances = project.getAssistances();
		this.projectManager = project.getProjectManager();
		this.files = project.getFiles();
		this.needs = project.getNeeds();
		this.actions = project.getActions();
		this.entrepreneurships = project.getEntrepreneurships();
		this.projectManagerName = this.projectManager != null ? this.projectManager.getName() : null;
		this.adminUsername =  adminUsername;
		this.adminEmail = adminEmail;
	}

	public DTOProject(Long id, String title, String description, List<File> files, List<Action> actions, List<Entrepreneurship> entrepreneurships) {
		this.id_Project = id;
		this.title = title;
		this.description = description;
		this.files = files;
		this.actions = actions;
		this.entrepreneurships = entrepreneurships;
		// Inicializa con el nombre del project manager o null si es null
		this.projectManagerName = this.projectManager != null ? this.projectManager.getName() : null;
		this.adminUsername =  null;
		this.adminEmail = null;
	}

	public DTOProject(Long id, String title, String description, String stage, Long idAdmin, ProjectManager projectManager,
                 List<File> files, List<Action> actions, List<Entrepreneurship> entrepreneurships) {
      this.id_Project = id;
      this.title = title;
      this.description = description;
      this.stage = stage;
      this.administrador = idAdmin;
      this.projectManager = projectManager;
      this.files = files;
      this.actions = actions;
      this.entrepreneurships = entrepreneurships;
      // Inicializa con el nombre del project manager o null si es null
      this.projectManagerName = this.projectManager != null ? this.projectManager.getName() : null;
      this.adminUsername =  null;
      this.adminEmail = null;
   }

	public DTOProject(Long id, String title, String description, String stage, Long idAdmin, ProjectManager projectManager,
					  List<File> files, List<Action> actions, List<Entrepreneurship> entrepreneurships,
					  List<Assistance> assistances, List<Need> needs) {
		this.id_Project = id;
		this.title = title;
		this.description = description;
		this.stage = stage;
		this.administrador = idAdmin;
		this.projectManager = projectManager;
		this.files = files;
		this.actions = actions;
		this.entrepreneurships = entrepreneurships;
		this.assistances = assistances;
		this.needs = needs;
		// Inicializa con el nombre del project manager o null si es null
		this.projectManagerName = this.projectManager != null ? this.projectManager.getName() : null;
		this.adminUsername =  null;
		this.adminEmail = null;

	}


}
