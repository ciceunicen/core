package com.project.Mapper;

import com.project.DTO.DTOProjectInsert;
import com.project.entities.Project;


public class Mapper {
    public Project toProject(DTOProjectInsert dtoProjectInsert){
        return new Project(dtoProjectInsert.getTitle(),dtoProjectInsert.getDescription(),dtoProjectInsert.getFiles(),dtoProjectInsert.getId_Admin());
    }
}
