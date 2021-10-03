package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Clientes;
import com.example.demo.models.Empleado;
import com.example.demo.repository.ClientesRepository;
import com.example.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public List<Empleado> findAll() {
		   return (List<Empleado>)empleadoRepository.findAll();
		}
	
	public Empleado findById(Integer idEmpleado) {
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}
	
	public void insert(Empleado empleado) {
			
		empleadoRepository.save(empleado);
	}
	
	public void update(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	public void delete(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	public void deleteFisico(Empleado empleado) {
		empleadoRepository.delete(empleado);
	}

}
