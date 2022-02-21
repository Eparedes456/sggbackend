package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Clientes;
import com.example.demo.models.Presentacion;
import com.example.demo.repository.ClientesRepository;
import com.example.demo.repository.PresentacionRepository;

@Service
public class PresentacionService {

	@Autowired
	private PresentacionRepository presentacionRepository;
	
	public List<Presentacion> findAll() {
		   return (List<Presentacion>)presentacionRepository.findAllPresentacion();
		}
	
	public Presentacion findById(Integer idCliente) {
		return presentacionRepository.findById(idCliente).orElse(null);
	}
	
	public void insert(Presentacion presentacion) {
			
		presentacionRepository.save(presentacion);
	}
	
	public void update(Presentacion presentacion) {
		presentacionRepository.save(presentacion);
	}
	
	public void delete(Presentacion presentacion) {
		presentacionRepository.save(presentacion);
	}
	
	public void deleteFisico(Presentacion presentacion) {
		presentacionRepository.delete(presentacion);
	}
	
}
