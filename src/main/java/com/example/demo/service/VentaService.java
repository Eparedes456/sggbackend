package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Venta> findAll() {
		   return (List<Venta>)ventaRepository.findAll();
	}
	
	public Venta findById(Integer idVenta) {
		return ventaRepository.findById(idVenta).orElse(null);
	}
	
	public void insert(Venta venta) {
		
		ventaRepository.save(venta);
	}
	
	public void update(Venta venta) {
		ventaRepository.save(venta);
	}
	
	public void delete(Venta venta) {
		ventaRepository.save(venta);
	}
	
	public void deleteFisico(Venta venta) {
		ventaRepository.delete(venta);
	}
	
}
