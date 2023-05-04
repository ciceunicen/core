package com.project.service.implementation;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.project.DTO.DTOEmail;
import com.project.service.EmailService;

@Service
public class EmailServiceImp implements EmailService{

	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	TemplateEngine templateEngine;
	@Value("${mail.urlFront}")
	private String urlFront;
	@Override
	public void sendEmail(DTOEmail dto) {

		MimeMessage message= javaMailSender.createMimeMessage() ;
		try {
			MimeMessageHelper helper= new MimeMessageHelper(message, true);
			Context context =new Context();
			Map<String,Object> model= new HashMap<>();
			model.put("name", dto.getName());
			model.put("url",urlFront+dto.getTokenPassword());
			context.setVariables(model);
			String htmlText= templateEngine.process("email-templates", context);
			helper.setFrom(dto.getMailFrom());
			helper.setTo(dto.getMailTo());
			helper.setSubject(dto.getSubject());
			helper.setText(htmlText,true);
			javaMailSender.send(message);
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}

}
