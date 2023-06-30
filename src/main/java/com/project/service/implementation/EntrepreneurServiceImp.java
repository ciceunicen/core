package com.project.service.implementation;

import com.project.DTO.DTOEntrepreneur;
import com.project.DTO.DTOEntrepreneurInsert;
import com.project.DTO.DTOEntrepreneurUpdate;
import com.project.entities.Project;
import com.project.entities.Role;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.entities.Entrepreneur;
import com.project.entities.User;
import com.project.repository.EntrepreneurRepository;
import com.project.service.EntrepreneurService;

import java.util.ArrayList;
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
	public DTOEntrepreneur postEntrepreneur(DTOEntrepreneurInsert e, Long currentUser_id) {
		Entrepreneur aux = new Entrepreneur(e.getDni(), e.getName(), e.getSurname(), e.getEmail(), e.getCuil_cuit(), e.getPhone(),
				e.getLocation(), e.getHowimetcice(), e.isIspf());

		if (currentUser_id != null) aux.setId_user(currentUser_id);
		aux = entrepreneurRepository.save(aux);

		DTOEntrepreneur dto = new DTOEntrepreneur(aux.getId(), aux.getDni(), aux.getName(), aux.getSurname(), aux.getEmail(),
			aux.getId_user(), aux.getIs_active(), aux.getCuil_cuit(), aux.getPhone(), aux.getLocation(), aux.getHowimetcice(),
				aux.isIspf(), aux.is_deleted());
		return dto;
	}

	/**
	 * Este metodo permite "activar/desactivar" un Emprendedor, si esta desactivado se activa y viceversa.
	 * @param id ID del Emprendedor para cambiarle su estado
	 * @return True si se modifico y False si no existe.
	 */
	public boolean setActive(Long id){
		Optional<Entrepreneur> e = entrepreneurRepository.findById(id);
		if (e.isPresent()) {
			e.get().setIs_active(!e.get().getIs_active());
			if (e.get().getId_user() != null) {
				if (e.get().getIs_active()) {
					Role r = roleRepository.findByType("Emprendedor");
					User userAux = userRepository.findById(e.get().getId_user()).get();
					userAux.addRole(r);
					userRepository.save(userAux);
				} else {
					Role r = roleRepository.findByType("Defecto");
					User userAux = userRepository.findById(e.get().getId_user()).get();
					userAux.addRole(r);
					userRepository.save(userAux);
				}
			}
			entrepreneurRepository.save(e.get());
			return true;
		}
		return false;
	}

	/**
	 * Este metodo permite editar un Emprendedor
	 * @param id ID del Emprendedor a editar
	 * @param e Los datos del JSON mapeados a un objeto DTOEmprendedorUpdate
	 * @return Retorna un DTOEmprendedor con sus modificaciones TAL cual quedo en la DB.
	 */
	@Override
	public DTOEntrepreneur editEntrepreneur(Long id, DTOEntrepreneurUpdate e, boolean restricted) {
		Entrepreneur edit = entrepreneurRepository.findById(id).get();
		if (edit.getIs_active() && restricted) {
			/**
			 * Si el perfil está activo y la acción restringida (usuario defecto o emprendedor modificando su propio perfil)
			 */
			edit.setEmail(e.getEmail());
			edit.setPhone(e.getPhone());
			edit.setLocation(e.getLocation());
			entrepreneurRepository.save(edit);
			return this.getEntrepreneurById(id);
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
			entrepreneurRepository.save(edit);
			return this.getEntrepreneurById(id);
		}
	}

	@Override
	public Iterable<DTOEntrepreneur> getEntrepreneurs() {
		List<DTOEntrepreneur> listaDTO = new ArrayList<>();
		Iterable<Entrepreneur> entrepreneurs = this.entrepreneurRepository.findAll();
		for (Entrepreneur e: entrepreneurs) {
			DTOEntrepreneur dto = new DTOEntrepreneur(e.getId(), e.getDni(), e.getName(), e.getSurname(), e.getEmail(), e.getId_user(),
					e.getIs_active(), e.getCuil_cuit(), e.getPhone(), e.getLocation(), e.getHowimetcice(), e.isIspf(), e.is_deleted());
			listaDTO.add(dto);
		}
		return listaDTO;
  }
  
	@Override
	public DTOEntrepreneur getEntrepreneurById(Long id) {
		Optional<Entrepreneur> o = entrepreneurRepository.findById(id);
		if (!o.isEmpty()) {
			Entrepreneur e = o.get();
			DTOEntrepreneur dto = new DTOEntrepreneur(e.getId(), e.getDni(), e.getName(), e.getSurname(), e.getEmail(), e.getId_user(),
					e.getIs_active(), e.getCuil_cuit(), e.getPhone(), e.getLocation(), e.getHowimetcice(), e.isIspf(), e.is_deleted());
			return dto;
		}
		return null;
	}

	@Override
	public DTOEntrepreneur deleteEntrepreneur(Long id) {
		entrepreneurRepository.deleteEntrepreneur(id);
		return this.getEntrepreneurById(id);
	}

	@Override
	public List<DTOEntrepreneur> getAllByFilters(List<String> filters,boolean deleted) {
		List<DTOEntrepreneur> list = new ArrayList<>();
		List<Entrepreneur> aux = entrepreneurRepository.findAll(filters,deleted);
		for (Entrepreneur e: aux) {
			DTOEntrepreneur dto = new DTOEntrepreneur(e.getId(), e.getDni(), e.getName(), e.getSurname(), e.getEmail(), e.getId_user(),
					e.getIs_active(), e.getCuil_cuit(), e.getPhone(), e.getLocation(), e.getHowimetcice(), e.isIspf(), e.is_deleted());
			list.add(dto);
		}
		return list;
	}


}

