package com.project.service.implementation;

import com.project.exception.NotFoundException;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.entities.Entrepreneur;
import com.project.entities.User;
import com.project.repository.EntrepreneurRepository;
import com.project.service.EntrepreneurService;

import java.util.Optional;

@Service
public class EntrepreneurServiceImp  implements EntrepreneurService{
	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Entrepreneur postEntrepeneur(Entrepreneur e) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		e.setId_user(u.getId());
		return entrepreneurRepository.save(e);
	}


	public boolean setActive(Long id){
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = userRepository.findById(u.getId()).get();
		Entrepreneur e= this.findById(id);
		if (usuario.getRole().getType().toLowerCase().equals("admin")||usuario.getRole().getType().toLowerCase().equals("superadmin")){
			e.setActive(!e.getActive());
			e.setId_user(usuario.getId());
			entrepreneurRepository.save(e);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Entrepreneur editEntreprenur(Long id,Entrepreneur e) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = userRepository.findById(u.getId()).get();
		if (usuario.getRole().getType().toLowerCase().equals("Emprendedor")) {
			Entrepreneur edit = entrepreneurRepository.findById(id).get();
			if (edit.getActive()) {
				edit.setEmail(e.getEmail());
				edit.setPhone(e.getPhone());
				edit.setLocation(e.getLocation());
				return entrepreneurRepository.save(edit);
			} else {
				//Entrepreneur edit = entrepreneurRepository.findById(id).get();
				edit.setEmail(e.getEmail());
				edit.setPhone(e.getPhone());
				edit.setLocation(e.getLocation());
				edit.setDni(e.getDni());
				edit.setHowimetcice(e.getHowimetcice());
				edit.setCuil_cuit(e.getCuil_cuit());
				edit.setName(e.getName());
				edit.setSurname(e.getSurname());
				return entrepreneurRepository.save(edit);
			}
		}
		/*
			En la task CICEDEV-90 se completa el return else.
			Que es cuando el que edita es un administrador del CICE y cambia lo que si puede editar.
		 */
		return e;


	}

	@Override
	public boolean existeID(Long id) {
		return entrepreneurRepository.existsById(id);
	}


	public Entrepreneur findById(Long id) {
		if(!entrepreneurRepository.existsById(id)) {
			throw new NotFoundException("No existe emprendedor con id: " +id);
		}
		return entrepreneurRepository.findById(id).get();
	}


}

