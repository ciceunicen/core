package com.project.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.util.*;

@Entity
@Table(name = "Project")
@Data
public class Project{
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
    private List<Assitance> assitances = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rel_Project_Need",
            joinColumns = @JoinColumn(name = "id_Project"),
            inverseJoinColumns = @JoinColumn(name="id_Need")
    )
    private List<Need> needs = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_Stage")
    @NotFound(action = NotFoundAction.IGNORE)
    private Stage stage = null;




    @Column
    private Long administrador;

    public Project(String title, String description, List<String> files, Long administrador) {
        this.title = title;
        this.description = description;
        this.files = new ArrayList<>();

        for (String file : files) {
            this.files.add(new File(file));
        }
        this.administrador = administrador;
    }

    public Project(String title, String description, String[] files, Long administrador) {
        this.title = title;
        this.description = description;
        this.files = new ArrayList<>();

        for (String file : files) {
            this.files.add(new File(file));
        }

        this.administrador = administrador;
    }

    public Project() {
    }


    public void addNeed(Need need) {
        needs.add(need);
    }

    public void addAssitance(Assitance assitance){
        this.assitances.add(assitance);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id_Project=" + id_Project +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectManager=" + projectManager +
                ", files=" + files +
                ", assitances=" + assitances +
                ", needs=" + needs +
                ", stage=" + stage +
                ", administrador=" + administrador +
                '}';
    }
}
