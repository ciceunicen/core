package com.project.DTO;

import com.project.entities.File;
import lombok.Data;

import java.util.List;

@Data
/**
 * datos para modificar en la base de datos que llegan desde front-end
 *
 */
public class DTOProjectUpdate {
    //Datos del proyecto
    private String title;
    private String description;
    private Long stage;
    private List<Long> assistances;
    private List<Long> files;
    private List<Long> needs;
    private List<File> newFiles;

}
