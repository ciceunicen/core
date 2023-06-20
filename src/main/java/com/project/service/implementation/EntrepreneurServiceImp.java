package com.project.service.implementation;

import com.project.entities.Role;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public Entrepreneur postEntrepreneur(Entrepreneur e, Long currentUser_id) {
		if (currentUser_id != null){
			e.setId_user(currentUser_id);
		}
		return entrepreneurRepository.save(e);
	}

	/**
	 * Este metodo permite "activar/desactivar" un Emprendedor, si esta desactivado se activa y viceversa.
	 * @param id ID del Emprendedor para cambiarle su estado
	 * @return True si se modifico y False si no existe.
	 */
	public boolean setActive(Long id){
		Entrepreneur e= entrepreneurRepository.findById(id).get();
		if (e.getId_user() != null) {
			e.setIs_active(!e.getIs_active());
			if (e.getIs_active()) {
				Role r = roleRepository.findByType("Emprendedor");
				User userAux = userRepository.findById(e.getId_user()).get();
				userAux.addRole(r);
				userRepository.save(userAux);
			} else {
				Role r = roleRepository.findByType("Defecto");
				User userAux = userRepository.findById(e.getId_user()).get();
				userAux.addRole(r);
				userRepository.save(userAux);
			}
			entrepreneurRepository.save(e);
			return true;
		}
		else return false;
	}

	/**
	 * Este metodo permite editar un Emprendedor
	 * @param id ID del Emprendedor a editar
	 * @param e Los datos del JSON mapeados a un objeto Emprendedor
	 * @return Retorna el Emprendedor con sus modificaciones TAL cual quedo en la DB.
	 */
	@Override
	public Entrepreneur editEntrepreneur(Long id, Entrepreneur e, boolean restricted) {
		Entrepreneur edit = entrepreneurRepository.findById(id).get();
		if (edit.getIs_active() && restricted) {
			/**
			 * Si el perfil está activo y la acción restringida (usuario defecto o emprendedor modificando su propio perfil)
			 */
			edit.setEmail(e.getEmail());
			edit.setPhone(e.getPhone());
			edit.setLocation(e.getLocation());
			return entrepreneurRepository.save(edit);
		} else {
			/**
			 * Si el perfil no está activo o la acción no está restringida (usuario defecto o emprendedor modificando su propio perfil)
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
			 * NUNCA se permite modificar ID, campo active, o ispf
			 */
			return entrepreneurRepository.save(edit);
		}
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

	@Override
	public Optional<Entrepreneur> deleteEntrepreneur(Long id) {
		entrepreneurRepository.deleteEntrepreneur(id);
		return this.getEntrepreneurById(id);
	}


}

