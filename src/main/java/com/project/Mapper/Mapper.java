package com.project.Mapper;

import java.util.List;

import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurInsert;
import com.project.DTO.DTOProjectInsert;
import com.project.DTO.DTOProjectManager;
import com.project.entities.Entrepreneur;
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

    public Entrepreneur toEntrepreneur(DTOEntrepreneurInsert e){
        return new Entrepreneur(e.getDni(), e.getName(), e.getSurname(), e.getEmail(), e.getCuil_cuit(), e.getPhone(),
                e.getLocation(), e.getHowimetcice(), e.isIspf());
    }

    public DTOEntrepreneur toDTOEntrepreneur(Entrepreneur aux){
        return new DTOEntrepreneur(aux.getId(), aux.getDni(), aux.getName(), aux.getSurname(), aux.getEmail(),
                aux.getId_user(), aux.getIs_active(), aux.getCuil_cuit(), aux.getPhone(), aux.getLocation(), aux.getHowimetcice(),
                aux.isIspf(), aux.is_deleted());
    }
}
