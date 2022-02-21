package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UnidadMedida;


@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Integer>{

	@Query("select undme from UnidadMedida  undme where undme.estado=true")
    List<UnidadMedida> findAllUndmedi();
	
}
