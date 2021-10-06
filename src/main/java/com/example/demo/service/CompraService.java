package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.models.Compra;
import com.example.demo.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	public List<Compra> findAll() {
		   return (List<Compra>)compraRepository.findAll();
	}
	
	public Compra findById(Integer idCompra) {
		return compraRepository.findById(idCompra).orElse(null);
	}
	
	public void insert(Compra compra) {
		
		compraRepository.save(compra);
	}
	
	public void update(Compra compra) {
		compraRepository.save(compra);
	}
	
	public void delete(Compra compra) {
		compraRepository.save(compra);
	}
	
	public void deleteFisico(Compra compra) {
		compraRepository.delete(compra);
	}
	
	
}
