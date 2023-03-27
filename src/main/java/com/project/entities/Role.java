package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "Role")
@Data
public class Role implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_role")
	private int id;
	
	@Column
	@NotEmpty
	private String type;
	public Role( @NotEmpty String type) {
		this.type = type;
	}
	public Role() {	
	} 
}
