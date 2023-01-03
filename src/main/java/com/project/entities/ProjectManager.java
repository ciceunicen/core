package com.project.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;

@Entity
@Table(name = "ProjectManager")
@Data
public class ProjectManager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ProjectManager;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String linkUnicen;
    @Column
    private String medioConocimientoCice;

    @OneToMany(mappedBy="projectManager",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private List<Project> projects;


    public ProjectManager(String name, String surname, String email, String phone, String linkUnicen, String medioConocimientoCice) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.linkUnicen = linkUnicen;
        this.medioConocimientoCice = medioConocimientoCice;
    }

    public ProjectManager() {

    }

    @Override
    public String toString() {
        return "ProjectManager{" +
                "id_ProjectManager=" + id_ProjectManager +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", linkUnicen='" + linkUnicen + '\'' +
                ", medioConocimientoCice='" + medioConocimientoCice + '\'' +
                '}';
    }
}
