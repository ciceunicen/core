package com.project.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class DTOEntrepreneurInsert {
    @Min(value = 7, message = "El dni debe contener como minimo 7 digitos")
    @Max(value = 20, message = "El dni debe contener como maximo 20 digitos")
    private Long dni;
    @NotBlank(message = "El nombre es obligatorio")
    @Length(max = 20, message = "El nombre debe contener como maximo 20 caracteres")
    private String name;
    @Length(max = 20, message = "El apellido debe contener como maximo 20 caracteres")
    private String surname;
    @Email(message = "El email enviado no es valido")
    @Length(max = 45, message = "El email debe contener como maximo 45 caracteres")
    private String email;

    private Long cuil_cuit;
    private Long phone;
    private String location;
    private String howimetcice;
    private boolean ispf;

    public DTOEntrepreneurInsert(Long dni, String name, String surname, String email, Long cuil_cuit, Long phone,
                                 String location, String howimetcice, boolean ispf) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cuil_cuit = cuil_cuit;
        this.phone = phone;
        this.location = location;
        this.howimetcice = howimetcice;
        this.ispf = ispf;
    }
}
