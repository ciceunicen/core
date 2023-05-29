package com.project.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 20, unique=true)
//    @NotEmpty                  ------- genera excepción de validación, tb en id_user, phone, etc...
    private Long dni;
    @Column(length = 20)
    @NotEmpty
    private String name;
    @Column(length = 20)
    @NotEmpty
    private String surname;
    @Column(nullable = true, length=45, unique=true)
    private String email;

    private Long id_user;		//Está guardando el id del usuario que registró al emprendedor, una vez que esta activo guarda el id del usuario responsable del cambio

    public Person(@NotEmpty Long dni, @NotEmpty String name, @NotEmpty String surname, String email, @NotEmpty Long id_user) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id_user = id_user;
    }

    public Person() {}

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

}
