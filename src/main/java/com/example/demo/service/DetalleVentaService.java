package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.DetalleVenta;
import com.example.demo.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	public List<DetalleVenta> findAll() {
		   return (List<DetalleVenta>)detalleVentaRepository.findAll();
	}
	
	public DetalleVenta findById(Integer idDetalleVenta) {
		return detalleVentaRepository.findById(idDetalleVenta).orElse(null);
	}
	
	public void insert(DetalleVenta detalleVenta) {
		
		detalleVentaRepository.save(detalleVenta);
	}
	
	public void update(DetalleVenta detalleVenta) {
		detalleVentaRepository.save(detalleVenta);
	}
	
	public void delete(DetalleVenta detalleVenta) {
		detalleVentaRepository.save(detalleVenta);
	}
	
	public void deleteFisico(DetalleVenta detalleVenta) {
		detalleVentaRepository.delete(detalleVenta);
	}
	
}
