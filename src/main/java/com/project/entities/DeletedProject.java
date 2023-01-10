package com.project.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Entity
@Table(name = "DeletedProjects")
@Data
public class DeletedProject implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_delete;
	
	@OneToOne(cascade=CascadeType.MERGE)//one-to-one
    @JoinColumn(name="id_Project")
	private Project project;
	
	@Column
	private Long id_admin;
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_delete;

	public DeletedProject() {
		super();
		this.date_delete = new Date();
	}
	
	
}
