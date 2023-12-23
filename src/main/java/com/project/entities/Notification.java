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

import com.project.DTO.DTONotificationInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
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
	private Boolean isRead;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_administrator")
	private User user;
	
	public Notification(DTONotificationInsert request) {
		this(request, null);
	}
	
	public Notification(DTONotificationInsert request, User user) {
		this.message = request.getMessage();
		this.date = request.getDate();
		this.isRead = false;
		this.user = user;
	}
	
	public Notification(String message, Date date, User user) {
		this.message = message;
		this.date = date;
		this.isRead = false;
		this.user = user;
	}
}
