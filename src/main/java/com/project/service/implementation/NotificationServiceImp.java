package com.project.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.DTONotificationInsert;
import com.project.entities.Notification;
import com.project.entities.User;
import com.project.exception.NotFoundException;
import com.project.repository.NotificationRepository;
import com.project.repository.UserRepository;
import com.project.service.NotificationService;

@Service
public class NotificationServiceImp implements NotificationService {
	 
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Notification> findAll() {
		return repository.findAll();
	}

	@Override
	public Notification findByid(Long id) {
		Optional<Notification> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotFoundException("No existe una notificación con el id " + id);
		}
	}
	
	@Override
	public List<Notification> findAllByUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<Notification> notifications = repository.findAllByUser(user);
			return notifications;
		} else {
			throw new NotFoundException("No existe un usuario con el id " + id);
		}
	}
	
	@Override
	public List<Notification> findAllByNotReadAndUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<Notification> notifications = repository.findAllByNotReadAndUser(user);
			return notifications;
		} else {
			throw new NotFoundException("No existe un usuario con el id " + id);
		}
	}
	
	@Override
	public Notification save(DTONotificationInsert request) {
		Optional<User> userOptional = userRepository.findById(request.getUserId());
		if (userOptional.isPresent()) {
			Notification notification = new Notification(request, userOptional.get());
			return repository.save(notification);
		} else {
			throw new NotFoundException("No existe un project manager con el id " + request.getUserId());
		}
	}

	@Override
	public Notification deleteByid(Long id) {
		Optional<Notification> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return optional.get();
		} else {
			throw new NotFoundException("No existe una notificación con el id " + id);
		}
	}	

}
