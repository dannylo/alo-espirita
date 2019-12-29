package com.aloespirita.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aloespirita.exceptions.UsuarioException;
import com.aloespirita.models.Usuario;
import com.aloespirita.services.UsuarioService;

@Component
public class AuthProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		try {
			Usuario usuario = this.usuarioService.getByLogin(user, password);
			return new UsernamePasswordAuthenticationToken(usuario.getEmail(), 
					usuario.getSenha(), 
					Collections.emptyList());
		} catch (UsuarioException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
