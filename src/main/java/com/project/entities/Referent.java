package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Referent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_project;
    private String id_user;
    private String telefono;
    private String localidad;
    private String mail;
    private String ocupacion;
    private String vinculacion;
    private String facultad;
    private String conocimiento;
    private String organizacion;

    protected Referent() {}

    public Referent(String userId, String telefono, String localidad, String mail, String ocupacion,
                    String vinculacion, String facultad, String conocimiento, String organizacion) {
        this.id_user = userId;
        this.telefono = telefono;
        this.localidad = localidad;
        this.mail = mail;
        this.ocupacion = ocupacion;
        this.vinculacion = vinculacion;
        this.facultad = facultad;
        this.conocimiento = conocimiento;
        this.organizacion = organizacion;
    }

    public void setProjectId(Long projectId) {
        this.id_project = projectId;
    }

    // Getters and setters for other fields

    @Override
    public String toString() {
        return "Referent{" +
                "id=" + id +
                ", projectId=" + id_project +
                ", userId='" + id_user + '\'' +
                ", telefono='" + telefono + '\'' +
                ", localidad='" + localidad + '\'' +
                ", mail='" + mail + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", vinculacion='" + vinculacion + '\'' +
                ", facultad='" + facultad + '\'' +
                ", conocimiento='" + conocimiento + '\'' +
                ", organizacion='" + organizacion + '\'' +
                '}';
    }
}
