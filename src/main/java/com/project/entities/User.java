package com.project.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Table(name = "User")
@Data
public class User implements Serializable,UserDetails{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, length=45)
    @NotEmpty
    @Email
	private String email;
	@Column
    @NotEmpty
	private String password;
	@Column(length = 20)
    @NotEmpty
	private String name;
	@Column(length = 20)
    @NotEmpty
	private String surname;
	private String tokenPassword;
	
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
	public String getTokenPassword() {
		return tokenPassword;
	}
	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword=tokenPassword;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
