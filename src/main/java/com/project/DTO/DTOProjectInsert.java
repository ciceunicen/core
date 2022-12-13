package com.project.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class DTOProjectInsert implements Serializable {
    //nombre, apellido y datos de contacto del responsable del proyecto
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String linkUnicen;
    private String medioConocimientoCice;

    //Datos del proyecto
    private String title;
    private String description;
    private String stage;
    private String assitanceType;
    private String files;

}
