package com.project.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.DTONotificationInsert;
import com.project.entities.Notification;
import com.project.entities.ProjectManager;
import com.project.exception.NotFoundException;
import com.project.repository.NotificationRepository;
import com.project.repository.ProjectManagerRepository;
import com.project.service.NotificationService;

@Service
public class NotificationServiceImp implements NotificationService {
	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;

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
	public List<Notification> findAllByProjectManager(Long id) {
		Optional<ProjectManager> projectManagerOptional = projectManagerRepository.findById(id);
		if (projectManagerOptional.isPresent()) {
			ProjectManager projectManager = projectManagerOptional.get();
			List<Notification> notifications = repository.findByProjectManager(projectManager);
			return notifications;
		} else {
			throw new NotFoundException("No existe un project manager con el id " + id);
		}
	}
	
	@Override
	public List<Notification> findByNotReadAndProjectManager(Long id) {
		Optional<ProjectManager> projectManagerOptional = projectManagerRepository.findById(id);
		if (projectManagerOptional.isPresent()) {
			ProjectManager projectManager = projectManagerOptional.get();
			List<Notification> notifications = repository.findByNotReadProjectManager(projectManager);
			return notifications;
		} else {
			throw new NotFoundException("No existe un project manager con el id " + id);
		}
	}

	@Override
	public Notification save(DTONotificationInsert request) {
		Optional<ProjectManager> projectManagerOptional = projectManagerRepository.findById(request.getProjectManagerId());
		if (projectManagerOptional.isPresent()) {
			Notification notification = new Notification(request, projectManagerOptional.get());
			return repository.save(notification);
		} else {
			throw new NotFoundException("No existe un project manager con el id " + request.getProjectManagerId());
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
