package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Productos;

@Repository
public interface ProductoRepository extends JpaRepository<Productos,Integer>{

}
