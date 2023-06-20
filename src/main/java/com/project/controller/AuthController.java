package com.project.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.DTO.DTOChangePassword;
import com.project.DTO.DTOEmail;
import com.project.POJO.AuthRequest;
import com.project.POJO.AuthResponse;
import com.project.entities.User;
import com.project.jwt.JwtTokenUtil;
import com.project.service.implementation.EmailServiceImp;
import com.project.service.implementation.UserServiceImp;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
    @Autowired EmailServiceImp emailService;
    @Autowired UserServiceImp userService;
    @Autowired PasswordEncoder passwordEncoder;
    @Value("${spring.mail.username}")
    private String emailFrom;
     
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
             
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            Object UsuarioResponse = new Object() {
                public String email = user.getEmail();
                public String rolType = user.getRole().getType();
                public String username = user.getUsername();
            };

            AuthResponse response = new AuthResponse(UsuarioResponse, accessToken);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/password/sendEmail")
    public void sendEmail(@RequestBody DTOEmail dto) {
    	User user = userService.findEmail(dto.getMailTo()).get();
    	dto.setMailFrom(emailFrom);
    	dto.setMailTo(user.getEmail());
		dto.setSubject("Cambio de contraseña");
		dto.setName(user.getUsername());
		UUID uuid= UUID.randomUUID();
		String tokenPassword= uuid.toString();
		dto.setTokenPassword(tokenPassword);
		user.setTokenPassword(tokenPassword);
		userService.saveUser(user);
    	emailService.sendEmail(dto);
	}
    
    @PostMapping("/resetPassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody DTOChangePassword  dto, BindingResult bindingResult){
    	if(!dto.getPassword().equals(dto.getConfirmPassword())) {
    		 return new ResponseEntity("Las contraseñas no coinciden",HttpStatus.BAD_REQUEST);
    	}
    	Optional<User> userOpt=userService.findByTokenPassword(dto.getTokenPassword());
    	if(!userOpt.isPresent()) {
    		return new ResponseEntity("No existe ningún usuario con ese token",HttpStatus.NOT_FOUND);
    	}else {
    		User user=userOpt.get();
    		String newPassword = passwordEncoder.encode(dto.getPassword());
    		user.setPassword(newPassword);
    		user.setTokenPassword(null);
    		userService.saveUser(user);
    		return new ResponseEntity("Contraseña actualizada",HttpStatus.OK);
    	}
    }

    @PostMapping("/password")
    public ResponseEntity<?> existMail(@RequestBody DTOEmail dto){
    	Optional<User> userOpt=userService.findEmail(dto.getMailTo());
    	if(!userOpt.isPresent()) {
    		return new ResponseEntity("No existe ningun usuario con esas credenciales",HttpStatus.NOT_FOUND);
    	}else {
    		return new ResponseEntity("Correo enviado con éxito",HttpStatus.OK);
    	}
    }

}
