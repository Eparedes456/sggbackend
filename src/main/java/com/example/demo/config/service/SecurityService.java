package com.example.demo.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import com.example.demo.config.security.token.JwtTokenUtil;
import com.example.demo.models.Usuarios;
import com.example.demo.repository.UsuarioRepository;

@Service
public class SecurityService {
	
	/*@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PermisoRepository permisoRepository;*/

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtTokenUtil tokenUtil;
	
	public boolean hasAccess(String solicitud, String accion, HttpServletRequest request)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
			IllegalArgumentException, UnsupportedEncodingException {
		String requestHeader = request.getHeader("Authorization");
		String token = requestHeader.substring(7);
		String username = tokenUtil.getUsernameFromToken(token);
		Usuarios usuario = usuarioRepository.findByLogin(username);
		
		return true;
	}

}
