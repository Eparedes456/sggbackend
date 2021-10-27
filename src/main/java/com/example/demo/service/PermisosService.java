package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.models.Permisos;
import com.example.demo.repository.PermisosRepository;

@Service
public class PermisosService {

	@Autowired
	private PermisosRepository permisosRepository;
	
	
	public List<Permisos> findAll() {
		   return (List<Permisos>)permisosRepository.findAll();
		}
	
	public Permisos findById(Integer idPermisos) {
		return permisosRepository.findById(idPermisos).orElse(null);
	}
	
	public void insert(Permisos permisos) {
			
		permisosRepository.save(permisos);
	}
	
	public void update(Permisos permisos) {
		permisosRepository.save(permisos);
	}
	
	public void delete(Permisos permisos) {
		permisosRepository.save(permisos);
	}
	
	public void deleteFisico(Permisos permisos) {
		permisosRepository.delete(permisos);
	}
	
}
