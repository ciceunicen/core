package com.project.DTO;

import com.project.entities.File;
import com.project.entities.Referent;

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
    private List<Long> assistances;
    private List<File> files;
    private List<Long> needs;
    private Long id_Admin;
    
 // Referent fields
    private String referent_userId;
    private String referent_telefono;
    private String referent_localidad;
    private String referent_mail;
    private String referent_ocupacion;
    private String referent_vinculacion;
    private String referent_facultad;
    private String referent_conocimiento;
    private String referent_organizacion;

}
