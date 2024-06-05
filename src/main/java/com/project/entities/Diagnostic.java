package com.project.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "diagnostic")
@Data
public class Diagnostic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String diagnostic;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_project")
    private Project project;
    
    @Column
    private Long idRecord;

    public Diagnostic() {}

    public Diagnostic(String diagnostic, Project project, Long idRecord) {
        this.diagnostic = diagnostic;
        this.project = project;
        this.idRecord = idRecord;
    }
}
