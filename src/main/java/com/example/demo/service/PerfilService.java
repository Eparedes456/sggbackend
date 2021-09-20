package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Perfil;

import com.example.demo.repository.PerfilRepository;




@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Perfil> findAll() {
		   return (List<Perfil>)perfilRepository.findAll();
		}
	
	public Perfil findById(Integer idPerfil) {
		return perfilRepository.findById(idPerfil).orElse(null);
	}
	
	
	public void insert(Perfil perfil) {
		
		perfilRepository.save(perfil);
	
	}
	
	public void update(Perfil perfil) {
		perfilRepository.save(perfil);
	}
	
	public void delete(Perfil perfil) {
		perfilRepository.save(perfil);
	}
	
	public void deleteFisico(Perfil perfil) {
		perfilRepository.delete(perfil);
	}

}
