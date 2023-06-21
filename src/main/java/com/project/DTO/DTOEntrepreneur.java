package com.project.DTO;

import lombok.Data;

@Data
public class DTOEntrepreneur {

    private Long id;
    private Long dni;
    private String name;
    private String surname;
    private String email;
    private Long id_user;

    private Boolean is_active = false;
    private Long cuil_cuit;
    private Long phone;
    private String location;
    private String howimetcice;
    private boolean ispf = true;
    private boolean is_deleted = false;

    public DTOEntrepreneur(Long id, Long dni, String name, String surname, String email, Long id_user, Boolean is_active, Long cuil_cuit, Long phone, String location, String howimetcice, boolean ispf, boolean is_deleted) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id_user = id_user;
        this.is_active = is_active;
        this.cuil_cuit = cuil_cuit;
        this.phone = phone;
        this.location = location;
        this.howimetcice = howimetcice;
        this.ispf = ispf;
        this.is_deleted = is_deleted;
    }
}
