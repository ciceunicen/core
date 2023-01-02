package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@Data
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Project;
    @Column
    @NotEmpty
    private String title;
    @Column
    @NotEmpty
    private String description;
    @Column
    @NotEmpty
    private String stage;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    @NotEmpty
    private List<Assitance> assitanceType;

    @ManyToOne
    @JoinColumn(name = "id_ProjectManager")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotFound(action = NotFoundAction.IGNORE)
    private ProjectManager projectManager;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    private List<File> files;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @NotEmpty
    private List<Need> needs;

    @Column
    private Long administrador;

    public Project(String title, String description, String stage, List<String> assitanceType, List<String> files,List<String> needs, Long administrador) {
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.files = new ArrayList<>();
        this.assitanceType = new ArrayList<>();
        this.needs = new ArrayList<>();

        for (String file : files) {
            this.files.add(new File(file));
        }
        for (String s : assitanceType) {
            this.assitanceType.add(new Assitance(s));
        }

        for (String need : needs){
            this.needs.add(new Need(need));
        }

        this.administrador = administrador;
    }

    public Project(String title, String description, String stage, String[] files,String[] needs, Long administrador) {
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.files = new ArrayList<>();
        this.assitanceType = new ArrayList<>();
        this.needs = new ArrayList<>();

        for (String file : files) {
            this.files.add(new File(file));
        }

        for (String need : needs){
            this.needs.add(new Need(need));
        }

        this.administrador = administrador;
    }

    public Project() {
    }
}
