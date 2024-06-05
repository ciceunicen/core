package com.project.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class Entrepreneur extends Person {

	@Builder.Default
	@Column(name = "active")
	private Boolean is_active = false;
	@Column(name = "cuil_cuit", length = 11, nullable = false, unique=true)
	private Long cuil_cuit;
	@Column(name = "phone", length=15)
	private Long phone;
	@Column
	private String location;

	@Column(name="howimetcice", length=60)
	private String howimetcice;
	@Builder.Default
	@Column(name="ispf")
	private boolean ispf = true;

	@Builder.Default
	@Column(name="deleted")
	private boolean is_deleted = false;

	public Entrepreneur() {	}

	public Entrepreneur(@NotEmpty Long dni, @NotEmpty String name, @NotEmpty String surname, String email,
						@NotEmpty Long cuilCuit, @NotEmpty Long phone, @NotEmpty String location, String howimetcice,
						boolean isPF) {
		super(dni, name, surname, email);
		this.cuil_cuit = cuilCuit;
		this.phone = phone;
		this.location = location;
		this.howimetcice= howimetcice;
		this.ispf = isPF;
	}

	@Override
	public String toString() {
		return "Entrepreneur [id=" + this.getId() + ", dni=" + this.getDni() + ", name=" + this.getName() +
				", surname=" + this.getSurname() + ", email=" + this.getEmail() + ", cuilCuit=" + cuil_cuit +
				", phone=" + phone + ", location=" + location +  ", howimetcice=" + howimetcice +
				", isPF=" + ispf + ", id_User=" + this.getId_user() + "]";
	}

}
