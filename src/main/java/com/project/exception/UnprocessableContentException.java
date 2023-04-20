package com.project.exception;

public class UnprocessableContentException extends RuntimeException {
	public UnprocessableContentException() {
		
	}
	public UnprocessableContentException(String mensaje) {
		super(mensaje);
	}

}
