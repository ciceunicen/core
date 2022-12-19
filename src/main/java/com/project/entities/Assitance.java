package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assitance")
@Data
public class Assitance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Assitance;
    @Column
    private String type;

    public Assitance(String type) {
        this.type = type;
    }

    public Assitance() {

    }
}
