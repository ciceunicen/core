package com.project.service.implementation;

import com.project.DTO.DTOUserUpdate;
import com.project.DTO.request.DtoUpdateDataUser;
import com.project.DTO.response.DTOeditableData;
import com.project.entities.Entrepreneur;
import com.project.entities.Role;
import com.project.repository.EntrepreneurRepository;
import com.project.repository.RoleRepository;


import java.util.Optional;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.exception.BadRequestException;
import com.project.exception.ConflictException;
import com.project.exception.NotFoundException;
import com.project.exception.UnprocessableContentException;
import com.project.repository.UserRepository;
import com.project.service.UserService;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImp implements UserService {

	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired EntrepreneurRepository entrepreneurRepository;
	
	private static final String prefijoEmailEliminado = "eliminado.";

	@Override
	public User postUser(User u) {
		if ((u.getEmail()==null || !u.getEmail().contains("@")||u.getEmail().isEmpty()) || u.getEmail().contains(prefijoEmailEliminado)){
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
		Optional<User> posibleUser = userRepo.findById(id);
		if(posibleUser.isEmpty()) { throw new NotFoundException("No existe ese user con el id: " + id); }

		return posibleUser.get();
	}

	@Override
	public User updateUser(Long id, DTOUserUpdate updatedUser) {
		// Busca el usuario por ID
		User userToUpdate = userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		// Aplica los cambios desde el DTO al usuario
		userToUpdate.setUsername(updatedUser.getUsername());
		userToUpdate.setEmail(updatedUser.getEmail());
		userToUpdate.setPassword(updatedUser.getNewPassword());

		// Guarda el usuario actualizado en la BD
		return userRepo.save(userToUpdate);
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

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public Optional<User> findByTokenPassword(String tokenPassword){
		return userRepo.findByTokenPassword(tokenPassword);
	}

	public boolean isPasswordCorrect(Long userId, String currentPassword) {
		Optional<User> userOptional = userRepo.findById(userId);

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String storedPassword = user.getPassword(); // Obtiene la contraseña almacenada

			// Compara contraseña almacenada con la contraseña proporcionada
			return passwordEncoder.matches(currentPassword, storedPassword);
		}
		else{
			throw new EntityNotFoundException("Usuario no encontrado con ID: " + userId);
		}

	}

	/**
	 * Borrado lógico de usuario por defecto.
	 * Si el usuario a eliminar no es un usuario por defecto o tiene una cuenta de emprendedor activa no es posible eliminarlo.
	 * Si el usuario por defecto tiene una cuenta de emprendedor no activa, esta también es eliminada.
	 * Al eliminar un admin se setea como eliminado y se duplica su registro pero con el rol de usuario defecto.
	 * Al eliminar un admin, se le agrega el string 'eliminado.' al principio del email (de la cuenta eliminada) para que no hayan problemas de integridad de usuarios duplicados en la base de datos  
	 * @param id el id del usuario a borrar
	 * @return el usuario eliminado
	 * @throws BadRequestException cuando no quiere eliminar un usuario que no es defecto o admin, el usuario tiene una cuenta de emprendedor activa o el usuario ya ha sido eliminado
	 * @throws NotFoundException cuando el usuario a eliminar no existe
	 */
	@Override
	public User deleteUser(Long id) {
		Optional<User> optional = userRepo.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			Role defecto = roleRepository.findByType("Defecto");
			Role admin = roleRepository.findByType("Admin");
			
			if (user.is_deleted()) {
				throw new BadRequestException("El usuario ya ha sido eliminado");
			}
			
			if (!user.getRole().getType().equals(defecto.getType()) && !user.getRole().getType().equals(admin.getType())) {
				throw new BadRequestException("El usuario a eliminar no es un usuario por defecto o un admin");
			}
			
			Optional<Entrepreneur> entrepreneurOptional = entrepreneurRepository.findByIdUserAndIsActive(id);
			if (entrepreneurOptional.isPresent()) {
				throw new BadRequestException("No es posible eliminar al usuario porque tiene una cuenta de emprendedor activa");
			}
			
			entrepreneurRepository.deleteByIdUserAndNoActive(id);
			
			String email = user.getEmail();
			if (user.getRole().getType().equals(admin.getType())) {
				// Elimina el email con prefijo por si anteriormente se elimino a un admin con este email y ahora se quiere conecrtir en admin al duplicado del mismo  
				userRepo.deleteByEmail(prefijoEmailEliminado + user.getEmail());
				
				user.setEmail(prefijoEmailEliminado + user.getEmail());
			}
			user.set_deleted(true);
			user = userRepo.save(user);
			
			User duplicatedUser = null;
			if (user.getRole().getType().equals(admin.getType())) {
				duplicatedUser = new User(email, user.getPassword());
				duplicatedUser.setUsername(user.getUsername());
				duplicatedUser.setRole(defecto);
				duplicatedUser.setTokenPassword(user.getTokenPassword());
				
				duplicatedUser = userRepo.save(duplicatedUser);
			}
			
			if (duplicatedUser != null) {
				return duplicatedUser;
			}
			return user;
		} else {
			throw new NotFoundException(String.format("El usuario con id %s no existe", id));
		}
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


	public Iterable<User> getUsersByRole(int id_rol) {
		return userRepo.findAllByRol(id_rol);
	}

	/*
	* Dado un id y la nueva informacion del usuario, actualiza los valores en la base de datos
	* (Si es que todos los campos están en orden, sino tira su excepcion correspondiente)
	* */
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
		currentDataUser.setUsername(newDataUser.name());
		// currentDataUser.setSurname(newDataUser.surname());
		currentDataUser.setEmail(newDataUser.email());
		// Actualizamos la informacion en la DB
		return userRepo.save(currentDataUser);
	}


}
