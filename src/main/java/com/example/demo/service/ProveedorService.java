package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Proveedor;
import com.example.demo.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	public List<Proveedor> findAll() {
		   return (List<Proveedor>)proveedorRepository.findAll();
	}
	
	public Proveedor findById(Integer idProveedor) {
		return proveedorRepository.findById(idProveedor).orElse(null);
	}
	
	public void insert(Proveedor proveedor) {
		
		proveedorRepository.save(proveedor);
	}
	
	public void update(Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}
	
	public void delete(Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}
	
	public void deleteFisico(Proveedor proveedor) {
		proveedorRepository.delete(proveedor);
	}
	
	
}
