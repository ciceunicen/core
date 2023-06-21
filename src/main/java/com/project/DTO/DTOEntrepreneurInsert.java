package com.project.DTO;

import lombok.Data;

@Data
public class DTOEntrepreneurInsert {

    private Long dni;
    private String name;
    private String surname;
    private String email;

    private Long cuil_cuit;
    private Long phone;
    private String location;
    private String howimetcice;
    private boolean ispf;

    public DTOEntrepreneurInsert(Long dni, String name, String surname, String email, Long cuil_cuit, Long phone,
                                 String location, String howimetcice, boolean ispf) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cuil_cuit = cuil_cuit;
        this.phone = phone;
        this.location = location;
        this.howimetcice = howimetcice;
        this.ispf = ispf;
    }
}
