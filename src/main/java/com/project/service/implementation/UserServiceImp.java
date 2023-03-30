package com.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired UserRepository userRepo;

	@Override
	public User postUser(User u) {
		
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(encodedPassword);*/
		return userRepo.save(u);
	}
}
