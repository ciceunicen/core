package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Entity
@Table(name = "User")
@Data
public class User implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
    @NotEmpty
	private String email;
	@Column
    @NotEmpty
	private String password;
	@Column
    @NotEmpty
	private String name;
	@Column
    @NotEmpty
	private String surname;
	
	@OneToOne
	@JoinColumn(name="id_role")
	private Role rol;

	public User(Long id, @NotEmpty String email, @NotEmpty String password, @NotEmpty String name,
			@NotEmpty String surname, Role rol) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.rol = rol;
	}
	
	public User() {
		
	}

}
