package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MesaRepository;
import com.example.demo.models.Mesa;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository mesaRepository;
	
	public List<Mesa> findAll() {
		   return (List<Mesa>)mesaRepository.findAll();
		}
	
	public Mesa findById(Integer idMesa) {
		return mesaRepository.findById(idMesa).orElse(null);
	}
	
	public void insert(Mesa mesa) {
		
		mesaRepository.save(mesa);
	}
	
	public void update(Mesa mesa) {
		mesaRepository.save(mesa);
	}
	
	public void delete(Mesa mesa) {
		mesaRepository.save(mesa);
	}
	
	public void deleteFisico(Mesa mesa) {
		mesaRepository.delete(mesa);
	}
}
