package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Producto_Categoria;

@Repository
public interface ProductoCategoryRepository extends JpaRepository<Producto_Categoria, Integer> {

}
