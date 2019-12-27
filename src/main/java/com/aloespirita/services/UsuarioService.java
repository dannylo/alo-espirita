package com.aloespirita.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloespirita.enums.ExceptionMessages;
import com.aloespirita.exceptions.UsuarioException;
import com.aloespirita.models.Usuario;
import com.aloespirita.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		return this.repository.save(usuario);
	}
	
	public Usuario getById(Long id) throws UsuarioException {
		Optional<Usuario> opt = this.repository.findById(id);
		if(!opt.isPresent()) {
			throw new UsuarioException(ExceptionMessages.USUARIO_NAO_ECONTRADO);
		}
		return opt.get();
	}
	
	public List<Usuario> getAll() {
		return repository.findAll();
	}
	

}
