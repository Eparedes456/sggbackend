package com.example.demo.config.security.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.models.Usuarios;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(Usuarios user, List<GrantedAuthority> authorities) {
		return new JwtUser(user.getIdUsuario(), user.getId_empleado(), user.getPerfil().getIdPerfil(),
				 user.getUsuario(),user.getContrasena(), authorities, true);
	}
	
	
}
