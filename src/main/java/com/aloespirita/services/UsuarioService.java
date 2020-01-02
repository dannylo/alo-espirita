package com.aloespirita.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
	
	public Usuario getByLogin(String email, String senha) throws UsuarioException {
		Usuario logado = this.repository.findByEmailAndSenha(email, senha);
		if (ObjectUtils.isEmpty(logado)) {
			throw new UsuarioException(ExceptionMessages.CREDENCIAIS_INVALIDAS);
		}
		logado.setUltimoLogin(LocalDate.now());
		logado = this.salvar(logado);
		return logado;
	}
	

}
