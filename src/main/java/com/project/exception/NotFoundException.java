package com.project.exception;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {
		
	}
	public NotFoundException(String mensaje) {
		super(mensaje);
	}
}
