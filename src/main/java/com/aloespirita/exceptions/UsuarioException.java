package com.aloespirita.exceptions;

import com.aloespirita.enums.ExceptionMessages;

public class UsuarioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private ExceptionMessages exceptionMessage;
	
	public UsuarioException(ExceptionMessages exceptionMessage) {
		super(exceptionMessage.getMessage());
		this.exceptionMessage = exceptionMessage;
	}
	
	public ExceptionMessages getExceptionMessage() {
		return this.exceptionMessage;
	}
}
