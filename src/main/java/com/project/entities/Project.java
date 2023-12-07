package com.project.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Project")
@Data
public class Project implements Serializable {//se removio extends entrepreneurships que venia de CompositeProject
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Project;
	@Column
	@NotEmpty
	private String title;
	@Column
	@NotEmpty
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_ProjectManager")
	@NotFound(action = NotFoundAction.IGNORE)
	private ProjectManager projectManager;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Project", referencedColumnName = "id_Project")
	private List<File> files;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "rel_Project_Assitance", joinColumns = @JoinColumn(name = "id_Project"), inverseJoinColumns = @JoinColumn(name = "id_Assitance"))
	private List<Assistance> assistances = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "rel_Project_Need", joinColumns = @JoinColumn(name = "id_Project"), inverseJoinColumns = @JoinColumn(name = "id_Need"))
	private List<Need> needs = new ArrayList<>();

	@OneToOne(cascade = CascadeType.MERGE) // one-to-one
	@JoinColumn(name = "id_Stage")
	private Stage stage;

	@Column
	private Long administrador;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Entrepreneurship> entrepreneurships;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Action> actions;
	
	@Column(nullable = false)
	private boolean is_active;

	public Project(String title, String description, List<File> files, Long administrador) {
		this.title = title;
		this.description = description;
		this.files = new ArrayList<>();
		this.entrepreneurships = new ArrayList<>();
		this.actions = new ArrayList<>();
		for (File file : files) {
			this.files.add(new File(file.getFile(), file.getType()));
		}
		this.administrador = administrador;
	}

	public Project(String title, String description, Long administrador) {
		this.title = title;
		this.description = description;
		this.files = new ArrayList<>();
		this.entrepreneurships = new ArrayList<>();
		this.actions = new ArrayList<>();
		for (File file : files) {
			this.files.add(new File(file.getFile(), file.getType()));
		}

		this.administrador = administrador;
	}

	public Project(@NotEmpty String title, String description, @NotEmpty LocalDate start_date) {
		this.title = title;
		this.description = description;
		
		this.entrepreneurships = new ArrayList<>();
	}

	public Project() {
	}

	public void addNeed(Need need) {
		needs.add(need);
	}

	public void addAssistance(Assistance assistance) {
		this.assistances.add(assistance);
	}

	// Metodos que estaban en CompositeProject Entities

	public void addEntrepreneurship(Entrepreneurship e) {
		this.entrepreneurships.add(e);
	}

	public boolean containsEntrepreneurship(Entrepreneurship e) {
		if (e == null) {
	        return false;
	    }
	    return entrepreneurships.contains(e);
	}
	
	 public void addAction (Action a){
	        this.actions.add(a);
	    }
	 
	 public Entrepreneurship getEntrepreneurshipById(Long entrepreneurshipId) {
		    for (Entrepreneurship entrepreneurship : entrepreneurships) {
		        if (entrepreneurship.getId().equals(entrepreneurshipId)) {
		            return entrepreneurship;
		        }
		    }
		    return null; // Retorna null si no se encuentra el emprendimiento con el ID especificado
		}

}
