package com.project.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
	@OneToOne(mappedBy = "user",  cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Entrepreneur entrepreneur;
	@Column(unique=true, length=45)
    @NotEmpty
	private String email;
	@Column(length = 20)
    @NotEmpty
	private String password;
	@Column(length = 20)
    @NotEmpty
	private String name;
	@Column(length = 20)
    @NotEmpty
	private String surname;
	
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role rol;

	public User(@NotEmpty String email, @NotEmpty String password, @NotEmpty String name,
			@NotEmpty String surname) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	
	
	public User() {
		
	}
	public void addRole(Role r) {
		this.rol=r;
	}

}
