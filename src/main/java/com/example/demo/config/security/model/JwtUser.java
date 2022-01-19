package com.example.demo.config.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Integer id;
	private final Integer id_empleado;
	private final Integer id_perfil;
	private final String usuario;
	private String contrasena;
	private String playerId;
	private final boolean estado;
	private final Collection<? extends GrantedAuthority> authorities;
	
	/**
	 *
	 * @param id
	 * @param nombreCompleto
	 * @param correo
	 * @param nickname
	 * @param clave
	 * @param authorities
	 * @param enabled
	 */
	public JwtUser(Integer id, Integer id_empleado, Integer id_perfil, String usuario, String contrasena,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		this.id = id;
		this.id_empleado = id_empleado;
		this.id_perfil = id_perfil;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.authorities = authorities;
		this.estado = enabled;
	}
	
	
	public String getPlayerId() {
		return playerId;
	}
	

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}



	public Integer getId() {
		return id;
	}


	public String getUsuario() {
		return usuario;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	

	public String getContrasena() {
		return contrasena;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return estado;
	}


	public Integer getId_empleado() {
		return id_empleado;
	}


	public Integer getId_perfil() {
		return id_perfil;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
