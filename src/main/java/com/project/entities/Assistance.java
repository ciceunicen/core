package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assistance")
@Data
public class Assistance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Assistance;
    @Column
    private String type;
    @Column(name = "is_default")
    private boolean isDefault=false;

    public Assistance(String type) {
        this.type = type;
    }

    public Assistance(String type, boolean is_default) {
        this.type = type;
        this.isDefault = is_default;
    }

    public Assistance() {

    }
}
