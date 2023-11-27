package com.project.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException() {
		super("No tiene permisos para realizar esta acción");
	}
}
