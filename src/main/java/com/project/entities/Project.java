package com.project.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Type;

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
    @Column
    private String assitanceType;
    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "id_ProjectManager")
    private ProjectManager projectManager;

    public Project(String title, String description, String stage, String assitanceType, ProjectManager projectManager) {
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.assitanceType = assitanceType;
        this.projectManager = projectManager;
    }

    public Project() {

    }
}
