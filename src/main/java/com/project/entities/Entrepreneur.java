package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Table(name = "Entrepreneur")
@Data
public class Entrepreneur implements Serializable{
	
	@Id
	@Column(name = "id_user")
	private Long id;
	@OneToOne
	@MapsId
	@JoinColumn(name = "id_user")
	private User user;
	@Column(name = "cuil_cuit", length = 11)
	private int cuilCuit;
	@Column (name = "phone_number")
	private int phoneNumber;
	@Column (name = "juridic_person")
	private int juridicPerson;
	
	public Entrepreneur(@NotEmpty User user, @NotEmpty int cuilCuit, @NotEmpty int phoneNumber, @NotEmpty int juridicPerson) {
		this.user = user;
		this.cuilCuit = cuilCuit;
		this.phoneNumber = phoneNumber;
		this.juridicPerson = juridicPerson;
	}
	
	public Entrepreneur() {
		
	}
	
}
