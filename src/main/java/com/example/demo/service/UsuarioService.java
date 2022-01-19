package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Usuarios;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuarios findByLogin(String login) {
		  return usuarioRepository.findByLogin(login);
		}
	
	public List<Usuarios> findAll() {
		   return (List<Usuarios>)usuarioRepository.findAll();
		}
	
	public Usuarios findById(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario).orElse(null);
	}
	
	public void insert(Usuarios usuario) {
		
		usuarioRepository.save(usuario);
	}
	
	public void update(Usuarios usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void delete(Usuarios usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void deleteFisico(Usuarios usuario) {
		usuarioRepository.delete(usuario);
	}
	
}
