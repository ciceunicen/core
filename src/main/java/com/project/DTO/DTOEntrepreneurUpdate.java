package com.project.DTO;

import lombok.Data;

@Data
public class DTOEntrepreneurUpdate {

    private Long dni;
    private String name;
    private String surname;
    private String email;

    private Long cuil_cuit;
    private Long phone;
    private String location;
    private String howimetcice;

    public DTOEntrepreneurUpdate(Long dni, String name, String surname, String email, Long cuil_cuit, Long phone, String location, String howimetcice) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cuil_cuit = cuil_cuit;
        this.phone = phone;
        this.location = location;
        this.howimetcice = howimetcice;
    }
}
