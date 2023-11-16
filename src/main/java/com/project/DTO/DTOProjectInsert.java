package com.project.DTO;

import com.project.entities.File;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
    private Long stage;
    private List<Long> assistanceType;
    private List<File> files;
    private List<Long> needs;
    private Long id_Admin;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

}
