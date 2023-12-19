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
	
	@GetMapping("/projectManager/{id}")
	public ResponseEntity<List<Notification>> findAllByProjectManager(@PathVariable Long projectManagerId) {
		return ResponseEntity.ok(service.findAllByProjectManager(projectManagerId)); 
	}
	
	@GetMapping("/notRead/projectManager/{id}")
	public ResponseEntity<List<Notification>> findAllByNotReadAndProjectManager(@PathVariable Long projectManagerId) {
		return ResponseEntity.ok(service.findAllByNotReadAndProjectManager(projectManagerId)); 
	}
}
