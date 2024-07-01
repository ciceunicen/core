package com.project.service.implementation;

import com.project.DTO.response.DTOReferent;
import com.project.entities.Project;
import com.project.entities.User;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Referent;
import com.project.service.ReferentService;
import com.project.repository.ReferentRepository;;import java.util.Optional;

@Service
public class ReferentServiceImp implements ReferentService {
	
	@Autowired
	private ReferentRepository referentRepository;
	@Autowired
	private ProjectServiceImp projetService;
	@Autowired
	private UserServiceImp userService;

	@Override
	public Referent addReferent(Referent newReferent) {
		Referent referent = referentRepository.save(newReferent);
		return referent;
	}

	@Override
	public DTOReferent findByIdproject(Long iDproject) {
		Referent referent = referentRepository.findByIdproject(iDproject);
		if(referent ==null){
			 throw new RuntimeException("No existe un referente asignado a este proyecto");
		}
		DTOReferent dto= new DTOReferent(referent);
		return dto;
	}

	@Override
	public DTOReferent UpdateReferent(DTOReferent referentDto,Long id_project) {
		Optional project= projetService.getProjectById(id_project);
		User user= userService.findById( Long.parseLong(referentDto.getId_user()));
		if(!project.isPresent()&&user!=null){
			throw new RuntimeException("El proyecto");
		}
		try {

			Referent referent= referentRepository.getReferenceById(referentDto.getId());
			referent.setMail(referentDto.getMail());
			referent.setFacultad(referentDto.getFacultad());
			referent.setConocimiento(referentDto.getConocimiento());
			referent.setOcupacion(referentDto.getOcupacion());
			referent.setTelefono(referentDto.getTelefono());
			referent.setVinculacion(referentDto.getVinculacion());
			referent.setId_user(referentDto.getId_user());
			referent.setOrganizacion(referentDto.getOrganizacion());
			referent.setLocalidad(referentDto.getLocalidad());
			referent.setProjectId(id_project);
			referentRepository.save(referent);

		}catch (Exception e){

			Referent referent= new Referent();
			referent.setMail(referentDto.getMail());
			referent.setFacultad(referentDto.getFacultad());
			referent.setConocimiento(referentDto.getConocimiento());
			referent.setOcupacion(referentDto.getOcupacion());
			referent.setTelefono(referentDto.getTelefono());
			referent.setVinculacion(referentDto.getVinculacion());
			referent.setId_user(referentDto.getId_user());
			referent.setOrganizacion(referentDto.getOrganizacion());
			referent.setLocalidad(referentDto.getLocalidad());
			referent.setProjectId(id_project);
			referentRepository.save(referent);
		}
		return null;
	}

}
