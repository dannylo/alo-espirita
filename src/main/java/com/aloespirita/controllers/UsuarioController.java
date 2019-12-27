package com.aloespirita.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloespirita.exceptions.UsuarioException;
import com.aloespirita.models.Usuario;
import com.aloespirita.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		Usuario usuarioSalvo = this.service.salvar(usuario);
		return ResponseEntity.ok().body(usuarioSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodos(){
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPorId(@PathVariable("id") Long id){
		try {
			return ResponseEntity.ok().body(service.getById(id));
		} catch (UsuarioException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
}
