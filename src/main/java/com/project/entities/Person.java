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
    private String email;  // Email de contacto =! al del UserLogin

    @Column(nullable = true, unique=true)
    private Long id_user;	//Este campo guarda el ID con el cual se esta logeando al sistema    <------
                            //Si el usuario es un usaurio Defecto se le setea el ID sino se deja en NULL por si es un administrador del CICE


    //private Long cice_id_active;  //Este campo va en EMPRENDEDOR .. por q si en un futuro esta la entidad AdministradorCICE rompe

    public Person(@NotEmpty Long dni, @NotEmpty String name, @NotEmpty String surname, String email) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {}

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

}
