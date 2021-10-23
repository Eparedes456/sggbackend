package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.models.Producto_Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoCategoryRepository;

@Service
public class ProductoCategoryService {

	@Autowired
	private ProductoCategoryRepository productoCategoryRepository;
	
	public List<Producto_Categoria> findAll() {
		   return (List<Producto_Categoria>)productoCategoryRepository.findAll();
		}
	
	public Producto_Categoria findById(Integer idProductoCategory) {
		return productoCategoryRepository.findById(idProductoCategory).orElse(null);
	}
	
	public void insert(Producto_Categoria productoCategory) {
			
		productoCategoryRepository.save(productoCategory);
	}
	
	public void update(Producto_Categoria productoCategory) {
		productoCategoryRepository.save(productoCategory);
	}
	
	public void delete(Producto_Categoria productoCategory) {
		productoCategoryRepository.save(productoCategory);
	}
	
	public void deleteFisico(Producto_Categoria productoCategory) {
		productoCategoryRepository.delete(productoCategory);
	}
	
}
