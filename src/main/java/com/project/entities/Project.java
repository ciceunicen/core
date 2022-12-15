package com.project.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@Data
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Project;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String stage;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    private List<Assitance> assitanceType;

    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "id_ProjectManager")
    private ProjectManager projectManager;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    private List<File> files;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    private List<Need> needs;

    @Column
    private Long administrador;

    public Project(String title, String description, String stage, List<String> assitanceType, ProjectManager projectManager,List<String> files,List<String> needs, Long administrador) {
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.projectManager = projectManager;
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

    public Project() {
    }
}
