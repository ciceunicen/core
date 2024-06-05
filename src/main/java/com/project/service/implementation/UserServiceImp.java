package com.project.service.implementation;

import com.project.DTO.request.DtoUpdateDataUser;
import com.project.DTO.response.DTOeditableData;
import com.project.entities.Role;
import com.project.repository.RoleRepository;


import java.util.Optional;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.exception.BadRequestException;
import com.project.exception.ConflictException;
import com.project.exception.NotFoundException;
import com.project.exception.UnprocessableContentException;
import com.project.repository.UserRepository;
import com.project.service.UserService;




@Service
public class UserServiceImp implements UserService {

	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepository;

	@Override
	public User postUser(User u) {
		if ((u.getEmail()==null || !u.getEmail().contains("@")||u.getEmail().isEmpty())){ 
			throw new BadRequestException("El email esta mal formateado");
		}
		else if(userRepo.isEmail(u.getEmail())!=null){
			throw new ConflictException("El email ya existe");
		}
		else if(u.getPassword().length()<8 || u.getPassword().length()>20) {
			throw new UnprocessableContentException("La contraseña es del largo equivocado(menor a 8 o mayor a 20 caracteres)");
		}
		else {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(u.getPassword());
		System.out.println("La password es " + u.getPassword());
		System.out.println("La password encryptada es " + encodedPassword);
		u.setPassword(encodedPassword);
		Role r = roleRepository.findByType("Defecto");
		if(r!= null) {
			u.setRol(r);
		}
		
		return userRepo.save(u);
		}
	}
	
	public User findById(Long id) {
		if(!userRepo.existsById(id)) {
			throw new NotFoundException("No existe ese user con ese" +id);
		}
		return userRepo.findById(id).get();
	}
	
	public Iterable<User> findAll(List<String> rolIds) {
		if(rolIds!=null) {
			return userRepo.findByRolIds(rolIds);
		}else {
			return userRepo.findAll();
		}
		
	}

	/**
	 * Hace administrador a un usuario dado
	 * @param id el usuario al que se le modifica el rol
	 * @return el usuario modificado
	 */
	@Override
	public User changeRole(Long id, Role role) {

		User user = userRepo.findById(id).get();//Busca el el udsuario por el id
		Role r = roleRepository.findById(role.getId()).get();//Busca el rol de admin para asignar

		user.addRole(r);//asigna el nuevo rol al usuario

		return userRepo.save(user);//persiste los datos en la base de datos

	}

	/*
	* A traves de un email busca en la DB los datos
	* que el usuario puede llegar a editar
	*/
	@Override
	public DTOeditableData getUpdatableData(String email) {
		Optional<DTOeditableData> response = userRepo.getUpdatableData(email);
		if(response.isEmpty()){ throw new NotFoundException("No existe ese user con email"); }
		return response.get();
    }

	@Override
	public User updateUserInformation(Long id, DtoUpdateDataUser newDataUser) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Traemos los datos actuales del usuario de la DB
		Optional<User> posibleUser = userRepo.findById(id);
		if (posibleUser.isEmpty()) {throw new NotFoundException("No existe usuario con ese id");}

		User currentDataUser = posibleUser.get();
		// Verificamos si el mail que mandaron es el mismo al del usuario guardado en la db
		// Si no es el mismo debemos de verificar si el nuevo email ya está tomado por otro usuario en la db
			if(!currentDataUser.getEmail().equals(newDataUser.email()) &&
			   userRepo.findByEmail(newDataUser.email()).isPresent()){
				throw new ConflictException("Ya existe un usuario con ese mail!");
			}


		// Buscar en repository la current password del usuairo para compararla con la que nos pasaron.
		// En caso de que coincidan realizar el update del usuario.

		// Caso contrario NO realizar el update (Las passwords no matchean).
		if(!passwordEncoder.matches(newDataUser.currentPassword(), currentDataUser.getPassword())){
			throw new BadRequestException("Las passwords ingresadas no matchean!");
		}

		if(!newDataUser.newPassword().isEmpty()){
			// Verificamos que la new password sea de almenos 8 caracteres y maximo 20 caracteres
			if(!(newDataUser.newPassword().matches("^[\\s\\S]{8,20}$"))){
				throw new BadRequestException("La nueva password no tiene entre 8 y 20 caracteres!");
			}
			// Verificamos que las nuevas contraseñas sean iguales
			if(!(newDataUser.newPassword().matches(newDataUser.newPasswordConfirmed()))){
				throw new BadRequestException("La nueva password no es igual a la confirmacion de la nueva password!");
			}
			currentDataUser.setPassword(passwordEncoder.encode(newDataUser.newPassword()));
		}

		// Asignamos a la entidad Usuario el nuevo nombre y mail
		currentDataUser.setName(newDataUser.name());
		// currentDataUser.setSurname(newDataUser.surname());
		currentDataUser.setEmail(newDataUser.email());
		// Actualizamos la informacion en la DB
		return userRepo.save(currentDataUser);
	}

	public Optional<User> findEmail(String email){
		return userRepo.findByEmail(email);
	}
	public void saveUser(User user) {
		 userRepo.save(user);
	}
	public Optional<User> findByTokenPassword(String tokenPaasword){
		return userRepo.findByTokenPassword(tokenPaasword);
	}
}
