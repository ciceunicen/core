package com.project.DTO.response;

import com.project.entities.Referent;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
public class DTOReferent {
    private Long id;
    private String id_user;
    private String telefono;
    private String localidad;
    private String mail;
    private String ocupacion;
    private String vinculacion;
    private String facultad;
    private String conocimiento;
    private String organizacion;

    public DTOReferent(Referent referent) {
        this.setId(referent.getId());
        this.setId_user(referent.getId_user());
        this.setMail(referent.getMail());
        this.setFacultad(referent.getFacultad());
        this.setLocalidad(referent.getLocalidad());
        this.setConocimiento(referent.getConocimiento());
        this.setOcupacion(referent.getOcupacion());
        this.setTelefono(referent.getTelefono());
        this.setVinculacion(referent.getVinculacion());
        this.setOrganizacion(referent.getOrganizacion());
    }
}
