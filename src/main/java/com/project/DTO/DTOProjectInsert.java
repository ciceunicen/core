package com.project.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
/**
 * datos para insertar en la base de datos que llegan desde front-end
 *
 */
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
    private List<String> assitanceType;
    private List<String> files;
    private List<String> needs;
    private Long id_Admin;

}
