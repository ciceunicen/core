package com.project.POJO;

import com.project.entities.Role;

import lombok.Data;

@Data
public class AuthResponse {

	private String email;
    private Integer rol;
    private String accessToken;
 
    public AuthResponse() { }
     
    public AuthResponse(String email, Integer rol, String accessToken) {
        this.email = email;
        this.rol = rol;
        this.accessToken = accessToken;
    }
}
