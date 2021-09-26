package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Empresa;

import com.example.demo.repository.EmpresaRepository;


@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> findAll() {
		   return (List<Empresa>)empresaRepository.findAll();
		}
	
	public Empresa findById(Integer idEmpresa) {
		return empresaRepository.findById(idEmpresa).orElse(null);
	}
	
	public void insert(Empresa empresa) {
			
		empresaRepository.save(empresa);
	}
	
	public void update(Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	public void delete(Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	public void deleteFisico(Empresa empresa) {
		empresaRepository.delete(empresa);
	}
	

}
