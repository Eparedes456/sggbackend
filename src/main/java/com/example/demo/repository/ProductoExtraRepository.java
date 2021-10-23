package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Producto_extra;

@Repository
public interface ProductoExtraRepository extends JpaRepository<Producto_extra, Integer> {

}
