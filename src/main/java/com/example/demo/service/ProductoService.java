package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Clientes;
import com.example.demo.models.Productos;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {
	
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Productos> findAll(){
		return (List<Productos>)productoRepository.findAll();
		
	}
	
	public Productos findById(Integer idProducto) {
		return productoRepository.findById(idProducto).orElse(null); 
	} 
	
	public void insert(Productos productos) {
			
		productoRepository.save(productos);
	}
	
	public void update(Productos productos) {
		productoRepository.save(productos);
	}
	
	public void delete(Productos productos) {
		productoRepository.save(productos);
	}
	
	public void deleteFisico(Productos productos) {
		productoRepository.delete(productos);
	}

}
