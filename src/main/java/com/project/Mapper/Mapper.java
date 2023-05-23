package com.project.Mapper;

import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectManager;
import com.project.entities.Project;
import com.project.entities.ProjectManager;


public class Mapper {
    public Project toProject(DTOProjectInsert dtoProjectInsert){
        return new Project(dtoProjectInsert.getTitle(),dtoProjectInsert.getDescription(),dtoProjectInsert.getFiles(),dtoProjectInsert.getId_Admin());
    }

    public ProjectManager toProjectManager(DTOProjectManager pmDTO){
        return new ProjectManager(pmDTO.getName(),pmDTO.getSurname(),pmDTO.getEmail(), pmDTO.getPhone(), pmDTO.getLinkUnicen(), pmDTO.getMedioConocimientoCice());
    }
}
