package com.project.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.DTO.DTONotificationInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
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
	@JoinColumn(name = "id_project_manager")
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
