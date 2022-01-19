package com.example.demo.config.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.model.JwtUserFactory;
import com.example.demo.models.Usuarios;
import com.example.demo.repository.UsuarioRepository;


@Service
public class AuthService implements UserDetailsService  {

	private Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * @Autowired private ContratoUsuarioRepository contratoUsuarioRepository;
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null) {
			logger.error("No Existe el usuario");
		}

		String nickname = username;
		Usuarios usuario = usuarioRepository.findByLogin(nickname.toUpperCase());

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("No existe el usuario '%s'.", username));
		} else {
			
			String perfil = usuario.getPerfil().getNombre_perfil();//"no asigando";
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(perfil));
			return JwtUserFactory.create(usuario, authorities);
		}
	}
	
}


