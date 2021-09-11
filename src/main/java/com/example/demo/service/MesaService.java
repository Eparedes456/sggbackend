package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MesaRepository;
import com.example.demo.models.Mesa;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository mesaRepository;
	
	public List<Mesa> findAll() {
		   return (List<Mesa>)mesaRepository.findAll();
		}

}
