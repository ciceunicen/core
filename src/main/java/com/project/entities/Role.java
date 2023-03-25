package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "Role")
@Data
public class Role implements Serializable{

	@Id
	@NotEmpty
	@Column (name="id_role")
	private int id;
	
	@Column
	@NotEmpty
	private String type;
	public Role(@NotEmpty int id, @NotEmpty String type) {
		super();
		this.id= id;
		this.type = type;
	}
	public Role() {
		
	}
}
