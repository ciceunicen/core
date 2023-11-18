package com.project.exception;

@SuppressWarnings("serial")
public class DeletedUserException extends RuntimeException {
	public DeletedUserException() {
		super("No fue posible iniciar sesión porque este usuario ha sido eliminado");
	}
}
