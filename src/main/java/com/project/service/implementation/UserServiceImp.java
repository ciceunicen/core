package com.project.service.implementation;

import com.project.entities.Role;
import com.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

	@Autowired UserRepository userRepo;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public User postUser(User u) {
		
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(encodedPassword);*/
		return userRepo.save(u);
	}

	/**
	 * Hace administrador a un usuario dado
	 * @param u el usuario al que se le modifica el rol
	 * @return el usuario modificado
	 */
	@Override
	public User makeAdmin(User u) {

		User user = userRepo.findById(u.getId()).get();//Busca el el udsuario por el id
		Role r = roleRepository.findById(1).get();//Busca el rol de admin para asignar

		user.addRole(r);//asigna el nuevo rol al usuario

		return user;
	}
}
