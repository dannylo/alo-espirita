package com.aloespirita.exceptions;

import com.aloespirita.enums.ExceptionMessages;

public class UsuarioException extends Exception {
	
	private ExceptionMessages message;
	
	public UsuarioException(ExceptionMessages message) {
		super(message.getMessage());
		this.message = message;
	}
}
