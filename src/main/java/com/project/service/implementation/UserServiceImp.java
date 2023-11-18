package com.project.service.implementation;

import com.project.entities.Entrepreneur;
import com.project.entities.Role;
import com.project.repository.EntrepreneurRepository;
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
	@Autowired EntrepreneurRepository entrepreneurRepository;

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
			u.setPassword(encodedPassword);

			Role r = roleRepository.findByType("Defecto");

			if(r!= null) {
				u.setRole(r);
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
	
	public Optional<User> findEmail(String email){
		return userRepo.findByEmail(email);
	}

	public void saveUser(User user) {
		 userRepo.save(user);
	}

	public Optional<User> findByTokenPassword(String tokenPassword){
		return userRepo.findByTokenPassword(tokenPassword);
	}

	/**
	 * Borrado lógico de usuario por defecto.
	 * Si el usuario a eliminar no es un usuario por defecto o tiene una cuenta de emprendedor activa no es posible eliminarlo 
	 * @param id el id del usuario a borrar
	 * @return el usuario eliminado
	 * @throws BadRequestException
	 * @throws NotFounException
	 */
	@Override
	public User deleteUser(Long id) {
		Optional<User> optional = userRepo.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			
			if (!user.getRole().getType().equals(roleRepository.findByType("Defecto").getType())) {
				throw new BadRequestException("El usuario a eliminar no es un usuario por defecto");
			}
			
			Optional<Entrepreneur> entrepreneurOptional = entrepreneurRepository.findByIdUserAndIsActive(id);
			if (entrepreneurOptional.isPresent()) {
				throw new BadRequestException("No es posible eliminar al usuario porque tiene una cuenta de emprendedor activa");
			}
			
			user.set_deleted(true);
			
			return userRepo.save(user);
		} else {
			throw new NotFoundException(String.format("El usuario con id %s no existe", id));
		}
	}

}
