package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.UnidadMedida;
import com.example.demo.repository.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {

	@Autowired
	private UnidadMedidaRepository unidadMeRepository;
	
	public List<UnidadMedida> findAll() {
		   return (List<UnidadMedida>)unidadMeRepository.findAllUndmedi();
	}
	
	public UnidadMedida findById(Integer idUnidadMedida) {
		return unidadMeRepository.findById(idUnidadMedida).orElse(null);
	}
	
	public void insert(UnidadMedida unidadmedida) {
			
		unidadMeRepository.save(unidadmedida);
	}
	
	public void update(UnidadMedida unidadmedida) {
		unidadMeRepository.save(unidadmedida);
	}
	
	public void delete(UnidadMedida unidadmedida) {
		unidadMeRepository.save(unidadmedida);
	}
	
	public void deleteFisico(UnidadMedida unidadmedida) {
		unidadMeRepository.delete(unidadmedida);
	}
	
}
