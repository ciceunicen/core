package com.project.DTO;

import lombok.Data;

@Data
/**
 * Datos para modificar en la base de datos que llegan desde el front-end para la entidad User
 */
public class DTOUserUpdate {
        private String username;
        private String email;
        private String password;

}

