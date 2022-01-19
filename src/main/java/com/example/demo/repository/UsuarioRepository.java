package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
	
	@Query("SELECT u FROM Usuarios u WHERE upper(u.usuario)=:username and estado = true")
    Usuarios findByLogin(String username);

}
