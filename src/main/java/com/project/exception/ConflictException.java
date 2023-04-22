package com.project.exception;

public class ConflictException extends RuntimeException{
	public ConflictException() {
		
	}
	public ConflictException(String mensaje) {
		super(mensaje);
	}

}
