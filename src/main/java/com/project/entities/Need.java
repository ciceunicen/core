package com.project.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "need")
@Data

public class Need {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Need;
    @Column
    private String needType;

    public Need(String needType) {
        this.needType = needType;
    }



    public Need() {

    }
}
