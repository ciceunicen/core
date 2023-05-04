package com.project.service;

import org.springframework.stereotype.Component;

import com.project.DTO.DTOEmail;

@Component
public interface EmailService {
	public void sendEmail(DTOEmail dto);

}
