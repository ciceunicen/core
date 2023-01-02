package com.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stage")
@Data
public class Stage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Stage;
    @Column
    private String stage_type;
    @OneToMany(mappedBy="stage", cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.MERGE}, fetch=FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    List<Project> projects;

    public Stage() {
    }

    public Stage(String stage_type) {
        this.stage_type = stage_type;
    }
}