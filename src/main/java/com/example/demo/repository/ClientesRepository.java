package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.models.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Integer>{
	
	@Query("select cli from Clientes  cli where cli.estado=true")
    List<Clientes> finAllCliente();

}
