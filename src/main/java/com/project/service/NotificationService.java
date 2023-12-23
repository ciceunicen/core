package com.project.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.DTO.DTONotificationInsert;
import com.project.entities.Notification;

@Component
public interface NotificationService {
	
	public List<Notification> findAll();
	public Notification findByid(Long id);
	public List<Notification> findAllByUser(Long id);
	public List<Notification> findAllByNotReadAndUser(Long id);
	public Notification save(DTONotificationInsert request);
	public Notification deleteByid(Long id);
}
