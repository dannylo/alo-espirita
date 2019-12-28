package com.aloespirita.enums;

public enum ExceptionMessages {
	
	USUARIO_NAO_ECONTRADO("Usuário não encontrado na base de dados."),
	CREDENCIAIS_INVALIDAS("E-mail ou senha inválidos.");
	
	private String message;
	
	private ExceptionMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return getMessage();
	}
	
}
