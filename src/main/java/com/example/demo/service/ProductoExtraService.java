package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Producto_Categoria;
import com.example.demo.models.Producto_extra;
import com.example.demo.repository.ProductoCategoryRepository;
import com.example.demo.repository.ProductoExtraRepository;

@Service
public class ProductoExtraService {

	@Autowired
	private ProductoExtraRepository productoExtraRepository;
	
	public List<Producto_extra> findAll() {
		   return (List<Producto_extra>)productoExtraRepository.findAll();
		}
	
	public Producto_extra findById(Integer idProductoCategory) {
		return productoExtraRepository.findById(idProductoCategory).orElse(null);
	}
	
	public void insert(Producto_extra productoExtra) {
			
		productoExtraRepository.save(productoExtra);
	}
	
	public void update(Producto_extra productoExtra) {
		productoExtraRepository.save(productoExtra);
	}
	
	public void delete(Producto_extra productoExtra) {
		productoExtraRepository.save(productoExtra);
	}
	
	public void deleteFisico(Producto_extra productoExtra) {
		productoExtraRepository.delete(productoExtra);
	}
	
}
