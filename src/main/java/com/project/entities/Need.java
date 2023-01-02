package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "need")
@Data

public class Need implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Need;
    @Column
    private String needType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_Project")
    private Project project;
    public Need(String needType) {
        this.needType = needType;
    }



    public Need() {

    }
}
