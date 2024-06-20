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
import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "El mensaje no puede estar vacio!")
	private String message;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Boolean isRead;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_administrator")
	private User user;
	
	public Notification(String message, User user) {
		this.message = message;
		this.date = new Date(System.currentTimeMillis());
		this.isRead = false;
		this.user = user;
	}
}
