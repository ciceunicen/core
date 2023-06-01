package com.project.service.implementation;

import com.project.entities.Project;
import com.project.entities.Role;
import com.project.exception.NotFoundException;
import com.project.repository.RoleRepository;
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

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurServiceImp  implements EntrepreneurService{

	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Entrepreneur postEntrepeneur(Entrepreneur e) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findById(u.getId()).get();
		if (user.getRole().getType().toLowerCase().equals("defecto")){
			e.setId_user(u.getId());
		}
		return entrepreneurRepository.save(e);
	}

	/**
	 * Este metodo permite "activar/desactivar" un Emprendedor, si esta desactivado se activa y viceversa.
	 * @param id ID del Emprendedor para cambiarle su estado
	 * @return True si se modifico (por q tenia permisos) y False si no tiene pernmisos.
	 */
	public boolean setActive(Long id){
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = userRepository.findById(u.getId()).get();
		Entrepreneur e= entrepreneurRepository.getById(id);
		if (usuario.getRole().getType().toLowerCase().equals("admin")||usuario.getRole().getType().toLowerCase().equals("superadmin")){
			e.setActive(!e.getActive());
			if (e.getActive()){
				Role r = roleRepository.findByType("Emprendedor");
				User userAux = userRepository.getById(e.getId_user());
				userAux.addRole(r);
				userRepository.save(userAux);
			}else{
				Role r = roleRepository.findByType("Defecto");
				User userAux = userRepository.getById(e.getId_user());
				userAux.addRole(r);
				userRepository.save(userAux);
			}

			entrepreneurRepository.save(e);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Este metodo permite editar un Emprendedor
	 * @param id ID del Emprendedor a editar
	 * @param e Los datos del JSON mapeados a un objeto Emprendedor
	 * @return Retorna el Emprendedor con sus modificaciones TAL cual quedo en la DB.
	 */
	@Override
	public Entrepreneur editEntreprenur(Long id,Entrepreneur e) {
		/**
		 * Si esta dentro de este metodo es por q es un admin o superAdmin modificando un Emprendedor (cualquiera sea),
		 * o es un Emprendedor o Defecto modificando SOLO su perfil.
		 */

		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usuario = userRepository.findById(u.getId()).get();
		/**
		 Si tiene permisos de Emprendedor, solo puede modificar los datos de contacto si se encuentra activo,
		 si no esta activo NO es un Emprendedor, entonces asumimos q es un Defecto,Admin o superAdmin y puede modificar todo.
		 */
		Entrepreneur edit = entrepreneurRepository.findById(id).get();
		if (usuario.getRole().getType().toLowerCase().equals("emprendedor")) {
			if (edit.getActive()) {
				edit.setEmail(e.getEmail());
				edit.setPhone(e.getPhone());
				edit.setLocation(e.getLocation());
				return entrepreneurRepository.save(edit);
			}
		}
		/**
		 * Si no salio por el if de getActivo (true) es por que es un admin o superAdmnin o es un Defecto que puede
		 * modificar sus datos por que no esta activo (y no es Emprendedor aun). Entonces se trata estos casos como iguales.
		 *
		 * Si tiene permisos de admin o superAdmin o si es Defecto y esta modificando su perfil por no estar activo aun,
		 * en este caso puede modificar todos los datos de un Emprendedor.
		 */

		edit.setEmail(e.getEmail());
		edit.setPhone(e.getPhone());
		edit.setLocation(e.getLocation());
		edit.setDni(e.getDni());
		edit.setHowimetcice(e.getHowimetcice());
		edit.setCuil_cuit(e.getCuil_cuit());
		edit.setName(e.getName());
		edit.setSurname(e.getSurname());
		/**
		 * No dejamos que modifique los ID, ni el campo active, ni el ispf
		 */
		return entrepreneurRepository.save(edit);

	}

	@Override
	public boolean existeID(Long id) {
		return entrepreneurRepository.existsById(id);
	}

	@Override
	public Iterable<Entrepreneur> getEntrepreneurs() {
		return entrepreneurRepository.findAll();
  }
  
	@Override
	public Optional<Entrepreneur> getEntrepreneurById(Long id) {
		return entrepreneurRepository.findById(id);
	}


}

