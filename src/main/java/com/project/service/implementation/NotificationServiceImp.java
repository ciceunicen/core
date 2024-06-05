package com.project.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTO.DTONotificationsAndReadQuantity;
import com.project.DTO.DTONotificationInsert;
import com.project.entities.Notification;
import com.project.entities.User;
import com.project.exception.NotFoundException;
import com.project.repository.NotificationRepository;
import com.project.repository.UserRepository;
import com.project.service.NotificationService;

@Service
@Transactional
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
	public DTONotificationsAndReadQuantity findAllByUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<Notification> notifications = repository.findAllByUser(user);
			Long readQuantity = repository.getNotReadNotificationsByUser(user);
			DTONotificationsAndReadQuantity DTONotifications = new DTONotificationsAndReadQuantity(notifications, readQuantity);
			
			return DTONotifications;
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
	
	public DTONotificationsAndReadQuantity setNotificationsAsReadeadByUser(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			repository.setNotificationsAsReadeadByUser(user);
			
			Long readQuantity = repository.getNotReadNotificationsByUser(user);
			List<Notification> notifications = repository.findAllByUser(user);
			DTONotificationsAndReadQuantity DTONotifications = new DTONotificationsAndReadQuantity(notifications, readQuantity);
			
			return DTONotifications;
		} else {
			throw new NotFoundException("El usuario con id " + userId + " no existe");
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
