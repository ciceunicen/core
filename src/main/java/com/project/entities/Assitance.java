package com.project.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "assitance")
@Data
public class Assitance {
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
