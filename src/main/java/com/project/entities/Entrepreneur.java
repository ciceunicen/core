package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "Entrepreneur")
@Data
@SuperBuilder
public class Entrepreneur implements Serializable{
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entrepreneur")
	private Long id;
	@Column(length = 20)
    @NotEmpty
	private String name;
	@Column(length = 20)
    @NotEmpty
	private String surname;
	@Column(unique=true, length=45)
    @NotEmpty
	private String email;
	@Column(name = "cuil_cuit", length = 11, nullable = false, unique=true)
	private Long cuil_cuit;
	@Column (name = "phone", length=15)
	private int phone;
	@Column(name="howknowcice", length=60)
	private String howknowcice;
	@Builder.Default
	@Column(name="ispf")
	private boolean ispf=true;
	@Column(name="id_user")
	private int id_user;
	
	
	
	public Entrepreneur() {
		
	}



	public Entrepreneur( String name, String surname, String email, Long cuilCuit, int phone, String howknowcice,
			boolean isPF, int id_User) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.cuil_cuit = cuilCuit;
		this.phone = phone;
		this.howknowcice = howknowcice;
		this.ispf = isPF;
		this.id_user = id_User;
	}



	@Override
	public String toString() {
		return "Entrepreneur [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", cuilCuit="
				+ cuil_cuit + ", phone=" + phone + ", howknowcice=" + howknowcice + ", isPF=" + ispf + ", id_User="
				+ id_user + "]";
	}




	

	
}
