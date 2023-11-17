package com.project.DTO;

import lombok.Data;

@Data
/**
 * Datos para modificar en la base de datos que llegan desde el front-end para la entidad User
 */
public class DTOUserUpdate {
        private String name;
        private String surname;
        private String email;
        private String password;

}

