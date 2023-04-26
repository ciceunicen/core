package com.project.POJO;

import com.project.entities.Role;

import lombok.Data;

@Data
public class AuthResponse {

	private String email;
    private String rolType;
    private String name;
    private String surname;
    private String accessToken;
 
    public AuthResponse() { }
     
    public AuthResponse(String email, String rolType, String name, String surname, String accessToken) {
        this.email = email;
        this.rolType = rolType;
        this.name = name;
        this.surname = surname;
        this.accessToken = accessToken;
    }
}
