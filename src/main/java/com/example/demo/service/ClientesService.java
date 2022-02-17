package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Clientes;
import com.example.demo.repository.ClientesRepository;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clienteRepository;
	
	public List<Clientes> findAll() {
		   return (List<Clientes>)clienteRepository.finAllCliente();
		}
	
	public Clientes findById(Integer idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}
	
	public void insert(Clientes clientes) {
			
		clienteRepository.save(clientes);
	}
	
	public void update(Clientes clientes) {
		clienteRepository.save(clientes);
	}
	
	public void delete(Clientes clientes) {
		clienteRepository.save(clientes);
	}
	
	public void deleteFisico(Clientes clientes) {
		clienteRepository.delete(clientes);
	}
	
	
}
