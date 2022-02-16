package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

	@Query("select ca from Categoria  ca where ca.estado=true")
    List<Categoria> finAllCategoria();
}
