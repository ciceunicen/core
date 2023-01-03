package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stage")
@Data
public class Stage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Stage;
    @Column
    private String stage_type;

    public Stage() {
    }

    public Stage(String stage_type) {
        this.stage_type = stage_type;
    }
}