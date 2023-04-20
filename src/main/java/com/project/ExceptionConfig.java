package com.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.BadRequestException;
import com.project.exception.ConflictException;
import com.project.exception.NotFoundException;
import com.project.exception.UnprocessableContentException;

@ControllerAdvice(annotations= RestController.class)
public class ExceptionConfig {
	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<?> notFoundException(Exception e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	@ExceptionHandler(value=ConflictException.class)
	public ResponseEntity<?> conflictException(Exception e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(value=BadRequestException.class)
	public ResponseEntity<?> badRequestException(Exception e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(value=UnprocessableContentException.class)
	public ResponseEntity<?> unprocessableContentException(Exception e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
	}
	

}
