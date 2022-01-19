package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token,Integer> {

	@Query("SELECT t FROM Token t WHERE t.usuario.idUsuario=:idUsuario AND t.hash=:token AND t.nombreEquipo=:userAgent")
	Token findByHashAndUsuario(String token,Integer idUsuario,String userAgent);
	
	@Modifying //??
	@Query("DELETE FROM Token t WHERE t.usuario.idUsuario=:idUsuario")
	void deleteByUsuario(Integer idUsuario);
	
}
