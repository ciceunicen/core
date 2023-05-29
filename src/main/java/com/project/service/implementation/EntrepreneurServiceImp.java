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
		System.out.println("USUARIO: "+ u );
		e.setId_user(u.getId());
		return entrepreneurRepository.save(e);
	}


	public ResponseEntity<?> setActive(Long id){
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = userRepository.findById(u.getId()).get();
		Entrepreneur e= this.findById(id);
		if (usuario.getRole().getType().toLowerCase().equals("admin")||usuario.getRole().getType().toLowerCase().equals("superadmin")){
			e.setActive(!e.getActive());
			e.setId_user(usuario.getId());
			entrepreneurRepository.save(e);
			return new ResponseEntity("Emprendedor validado con exito",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No tiene permisos de administrador",HttpStatus.UNAUTHORIZED);
		}
	}


	public Entrepreneur findById(Long id) {
		if(!entrepreneurRepository.existsById(id)) {
			throw new NotFoundException("No existe emprendedor con id: " +id);
		}
		return entrepreneurRepository.findById(id).get();
	}


}

