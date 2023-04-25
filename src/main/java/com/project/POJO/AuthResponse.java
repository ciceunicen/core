package com.project.POJO;

import com.project.entities.Role;

import lombok.Data;

@Data
public class AuthResponse {

	private String email;
    private Integer rolID;
    private String rolType;
    private String accessToken;
 
    public AuthResponse() { }
     
    public AuthResponse(String email, Role rol, String accessToken) {
        this.email = email;
        this.rolID = rol.getId();
        this.rolType = rol.getType();
        this.accessToken = accessToken;
    }
}
