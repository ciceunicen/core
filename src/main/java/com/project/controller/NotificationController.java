package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Notification;
import com.project.service.implementation.NotificationServiceImp;

@RestController
@RequestMapping("notifications")
public class NotificationController {

	@Autowired
	private NotificationServiceImp service;
	
	@GetMapping("")
	public ResponseEntity<List<Notification>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Notification> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByid(id)); 
	}
	
	@GetMapping("/projectManager/{userId}")
	public ResponseEntity<List<Notification>> findAllByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(service.findAllByUser(userId)); 
	}
	
	@GetMapping("/notRead/projectManager/{userId}")
	public ResponseEntity<List<Notification>> findAllByNotReadAndUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(service.findAllByNotReadAndUser(userId)); 
	}
}
