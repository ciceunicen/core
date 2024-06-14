package com.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.BadRequestException;
import com.project.exception.ConflictException;
import com.project.exception.DeletedUserException;
import com.project.exception.NotFoundException;
import com.project.exception.UnauthorizedException;
import com.project.exception.UnprocessableContentException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations= RestController.class)
public class ExceptionConfig {
	@ExceptionHandler(value=DeletedUserException.class)
	public ResponseEntity<?> deletedUserException(DeletedUserException e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	@ExceptionHandler(value=UnauthorizedException.class)
	public ResponseEntity<?> unauthorizedException(UnauthorizedException e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}
	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<?> notFoundException(Exception e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	@ExceptionHandler(value=ConflictException.class)
	public ResponseEntity<?> conflictException(Exception e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(value=BadRequestException.class)
	public ResponseEntity<?> badRequestException(Exception e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	@ExceptionHandler(value=UnprocessableContentException.class)
	public ResponseEntity<?> unprocessableContentException(Exception e){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
	}
	

}
