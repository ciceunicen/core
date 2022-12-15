package com.project.Mapper;

import com.project.DTO.DTOProjectInsert;
import com.project.entities.Project;
import com.project.entities.ProjectManager;


public class Mapper {
    public Project toProject(DTOProjectInsert dtoProjectInsert){
        return new Project(dtoProjectInsert.getTitle(),dtoProjectInsert.getDescription(),dtoProjectInsert.getStage(),dtoProjectInsert.getAssitanceType(),
                new ProjectManager(dtoProjectInsert.getName(),dtoProjectInsert.getSurname(),dtoProjectInsert.getEmail(),dtoProjectInsert.getPhone(),
                        dtoProjectInsert.getLinkUnicen(),dtoProjectInsert.getMedioConocimientoCice()),dtoProjectInsert.getFiles()
                ,dtoProjectInsert.getNeeds(),dtoProjectInsert.getId_Admin());
    }
}
