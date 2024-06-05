package com.project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm",locale = "es_AR", timezone = "GMT-3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public AdministrationRecords() {
    }

    public AdministrationRecords(Project project, String action) {
        this.project = project;
        this.id_admin = project.getAdministrador();
        this.action = action;
        this.date = new Date();
    }

    public AdministrationRecords(Project project, Long id_admin, String action) {
        this.project = project;
        this.id_admin = id_admin;
        this.action = action;
        this.date = new Date();
    }
}
