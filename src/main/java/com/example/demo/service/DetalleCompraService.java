package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Compra;
import com.example.demo.models.DetalleCompra;
import com.example.demo.repository.CompraRepository;
import com.example.demo.repository.DetalleCompraRepository;

@Service
public class DetalleCompraService {
	
	@Autowired
	private DetalleCompraRepository detalleCompraRepository;
	
	public List<DetalleCompra> findAll() {
		   return (List<DetalleCompra>)detalleCompraRepository.findAll();
	}
	
	public DetalleCompra findById(Integer idDetalleCompra) {
		return detalleCompraRepository.findById(idDetalleCompra).orElse(null);
	}
	
	public void insert(DetalleCompra detallecompra) {
		
		detalleCompraRepository.save(detallecompra);
	}
	
	public void update(DetalleCompra detallecompra) {
		detalleCompraRepository.save(detallecompra);
	}
	
	public void delete(DetalleCompra detallecompra) {
		detalleCompraRepository.save(detallecompra);
	}
	
	public void deleteFisico(DetalleCompra detallecompra) {
		detalleCompraRepository.delete(detallecompra);
	}
	
	
}
