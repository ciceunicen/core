package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AdministrationRecords")
@Data
public class AdministrationRecords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_record;
    @OneToOne(cascade=CascadeType.MERGE)//one-to-one
    @JoinColumn(name="id_Project")
    private Project project;
    @Column
    private Long id_admin;
    @Column
    private String action;
    @Column
    private Date date;

    public AdministrationRecords() {
    }

    public AdministrationRecords(Project project, String action) {
        this.project = project;
        this.id_admin = project.getAdministrador();
        this.action = action;
        this.date = new Date();
    }
}
