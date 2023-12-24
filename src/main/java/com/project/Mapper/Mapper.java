package com.project.Mapper;

import java.util.List;

import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectManager;
import com.project.entities.Project;
import com.project.entities.ProjectManager;
import com.project.entities.Referent;


public class Mapper {

    public Project toProject(DTOProjectInsert dtoProjectInsert){
        return new Project(dtoProjectInsert.getTitle(),dtoProjectInsert.getDescription(),dtoProjectInsert.getFiles(),dtoProjectInsert.getId_Admin());
    }

    public ProjectManager toProjectManager(DTOProjectManager pmDTO){
        return new ProjectManager(pmDTO.getName(),pmDTO.getSurname(),pmDTO.getEmail(), pmDTO.getPhone(), pmDTO.getLinkUnicen(), pmDTO.getMedioConocimientoCice());
    }

	public Referent toReferent(DTOProjectInsert dtoProjectInsert) {
		return new Referent(dtoProjectInsert.getReferent_userId(), dtoProjectInsert.getReferent_telefono(), dtoProjectInsert.getReferent_localidad(),
				dtoProjectInsert.getReferent_mail(), dtoProjectInsert.getReferent_ocupacion(), dtoProjectInsert.getReferent_vinculacion(), dtoProjectInsert.getReferent_facultad(),
				dtoProjectInsert.getReferent_conocimiento(), dtoProjectInsert.getReferent_organizacion());
	}
}
