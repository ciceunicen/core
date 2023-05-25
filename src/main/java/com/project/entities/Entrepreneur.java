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
	@Column
	private Boolean active = false;
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

	public Entrepreneur() {	}

	public Entrepreneur(@NotEmpty Long dni, @NotEmpty String name, @NotEmpty String surname, String email, Long id_user,
						@NotEmpty Long cuilCuit, @NotEmpty Long phone, String howimetcice, boolean isPF) {
		super(dni, name, surname, email, id_user);
		this.cuil_cuit = cuilCuit;
		this.phone = phone;
		this.howimetcice= howimetcice;
		this.ispf = isPF;
	}

	@Override
	public String toString() {
		return "Entrepreneur [id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() +
				", email=" + this.getEmail() + ", cuilCuit=" + cuil_cuit + ", phone=" + phone + ", howimetcice=" +
				howimetcice + ", isPF=" + ispf + ", id_User=" + this.getId_user() + "]";
	}

}
