package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Caja;

import com.example.demo.repository.CajaRepository;

@Service
public class CajaService {

	@Autowired
	private CajaRepository cajaRepository;
	
	public List<Caja> findAll() {
		   return (List<Caja>)cajaRepository.findAllCaja();
		}
	
	public Caja findById(Integer idCliente) {
		return cajaRepository.findById(idCliente).orElse(null);
	}
	
	public void insert(Caja cajas) {
			
		cajaRepository.save(cajas);
	}
	
	public void update(Caja cajas) {
		cajaRepository.save(cajas);
	}
	
	public void delete(Caja cajas) {
		cajaRepository.save(cajas);
	}
	
	public void deleteFisico(Caja cajas) {
		cajaRepository.delete(cajas);
	}
	
}
