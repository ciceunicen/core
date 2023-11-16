package com.project.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.entities.Action;
import com.project.entities.Assistance;
import com.project.entities.Entrepreneurship;
import com.project.entities.File;
import com.project.entities.Need;
import com.project.entities.ProjectManager;
import lombok.Data;

@Data
public class DTOProject {

	private Long id_Project;
	private String title;
	private String description;
	private String stage;
	private List<Assistance> assistanceType;
	private ProjectManager projectManager;
	private List<File> files;
	private List<Need> needs;

	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start_date;
	private List<Action> actions;

	private List<Entrepreneurship> entrepreneurships;

	public DTOProject(Long id, String title, String description, LocalDate start_date, List<File> files,
			List<Action> actions, List<Entrepreneurship> entrepreneurships) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.files = files;
		this.actions = actions;
		this.entrepreneurships = entrepreneurships;
	}
}
