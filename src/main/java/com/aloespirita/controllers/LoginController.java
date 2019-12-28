package com.aloespirita.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloespirita.dtos.Login;
import com.aloespirita.exceptions.UsuarioException;
import com.aloespirita.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> realizarLogin(@RequestBody Login login){
		try {
			return ResponseEntity.ok().body(this.usuarioService
					.getByLogin(login.getEmail(), login.getSenha()));
		} catch (UsuarioException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
