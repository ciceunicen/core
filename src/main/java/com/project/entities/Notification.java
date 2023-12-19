package com.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.project.DTO.DTONotificationInsert;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String message;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Boolean read;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProjectManager projectManager;
	
	public Notification(DTONotificationInsert request) {
		this(request, null);
	}
	
	public Notification(DTONotificationInsert request, ProjectManager projectManager) {
		this.message = request.getMessage();
		this.date = request.getDate();
		this.projectManager = projectManager;
	}
}
