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
		System.out.println("La current que ingresamos es: " + newDataUser.currentPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Traemos los datos actuales del usuario de la DB
		Optional<User> posibleUser = userRepo.findById(id);
		if(posibleUser.isEmpty()){throw new NotFoundException("No existe usuario con ese id");}

		User currentDataUser = posibleUser.get();
		String result;


		if(passwordEncoder.matches(newDataUser.currentPassword(), currentDataUser.getPassword())){
			System.out.println("SON iguales");

			// Verificamos que la new password sea de almenos 8 caracteres y maximo 20 caracteres
			// if(newDataUser.getNewPassword().matches("/^[\\s\\S]{8,20}$/")){ throw new Exception("La nueva password no tiene entre 8 y 20 caracteres!"); }
			// if(newDataUser.getNewPassword().matches(newDataUser.getNewPasswordConfirmed())){ throw new Exception("La nueva password no es igual a la confirmacion de la nueva password!"); }

			// Asignamos a la entidad Usuario la nueva contraseña, mail y nombre
			currentDataUser.setPassword(passwordEncoder.encode(newDataUser.newPassword()));
			currentDataUser.setName(newDataUser.name());
			// currentDataUser.setSurname(newDataUser.surname());
			currentDataUser.setEmail(newDataUser.email());
			// Actualizamos la informacion en la DB
			return userRepo.save(currentDataUser);
		}else{
			System.out.println("NO son iguales");
			result = "NO SON IGUALES";
			return new User();
		}


		// Buscar en repository la current password del usuairo para compararla con la que nos pasaron.
		// En caso de que coincidan realizar el update del usuario.
		// En caso de el usuario ingresara una nueva contraseña hay que hashearla antes de guardarla.

		// Caso contrario NO realizar el update (Las passwords no matchean).
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
