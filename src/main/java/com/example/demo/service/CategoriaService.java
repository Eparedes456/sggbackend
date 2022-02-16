package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriarepository;
	
	public List<Categoria> findAll() {
		   return (List<Categoria>)categoriarepository.finAllCategoria();
		}
	
	public Categoria findById(Integer idCategoria) {
		return categoriarepository.findById(idCategoria).orElse(null);
	}
	
	public void insert(Categoria categoria) {
			
		categoriarepository.save(categoria);
	}
	
	public void update(Categoria categoria) {
		categoriarepository.save(categoria);
	}
	
	public void delete(Categoria categoria) {
		categoriarepository.save(categoria);
	}
	
	public void deleteFisico(Categoria categoria) {
		categoriarepository.delete(categoria);
	}
	
}
