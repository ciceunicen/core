package com.project.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.io.Serializable;
import java.util.*;

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

    @ManyToOne
    @JoinColumn(name = "id_ProjectManager")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProjectManager projectManager;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Project",referencedColumnName = "id_Project")
    private List<File> files;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rel_Project_Assitance",
            joinColumns = @JoinColumn(name = "id_Project"),
            inverseJoinColumns = @JoinColumn(name="id_Assitance")
    )
    private List<Assistance> assistances = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rel_Project_Need",
            joinColumns = @JoinColumn(name = "id_Project"),
            inverseJoinColumns = @JoinColumn(name="id_Need")
    )
    private List<Need> needs = new ArrayList<>();

    @OneToOne(cascade=CascadeType.MERGE)//one-to-one
    @JoinColumn(name="id_Stage")
    private Stage stage ;

    @Column
    private Long administrador;


    public Project(String title, String description, List<File> files, Long administrador) {
        this.title = title;
        this.description = description;
        this.files = new ArrayList<>();

        for (File file : files) {
            this.files.add(new File(file.getFile(),file.getType()));
        }
        this.administrador = administrador;
    }

    public Project(String title, String description, Long administrador) {
        this.title = title;
        this.description = description;
        this.files = new ArrayList<>();

        for (File file : files) {
            this.files.add(new File(file.getFile(),file.getType()));
        }

        this.administrador = administrador;
    }

    public Project() {
    }


    public void addNeed(Need need) {
        needs.add(need);
    }

    public void addAssistance(Assistance assistance){
        this.assistances.add(assistance);
    }

}
