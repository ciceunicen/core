package com.project.DTO;

import lombok.Data;
import java.util.List;

@Data
/**
 * datos para insertar en la base de datos que llegan desde front-end
 *
 */
public class DTOProjectInsert{
    //id responsable del proyecto
    private Long id_ProjectManager;

    //Datos del proyecto
    private String title;
    private String description;
    private String stage;
    private List<String> assitanceType;
    private List<String> files;
    private List<String> needs;
    private Long id_Admin;

}
