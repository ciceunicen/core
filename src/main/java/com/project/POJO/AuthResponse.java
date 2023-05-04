package com.project.POJO;

import com.project.entities.Role;
import com.project.entities.User;

import lombok.Data;

@Data
public class AuthResponse {

    private Object usuario;
    private String accessToken;
 
    public AuthResponse() { }
     
    public AuthResponse(Object usuario, String accessToken) {
        this.usuario = usuario;
        this.accessToken = accessToken;
    }
}
