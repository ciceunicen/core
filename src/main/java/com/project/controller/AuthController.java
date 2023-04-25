package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.project.POJO.AuthRequest;
import com.project.POJO.AuthResponse;
import com.project.entities.User;
import com.project.jwt.JwtTokenUtil;

@RestController
public class AuthController {

	@Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
     
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
             
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), user.getRol(), accessToken);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
