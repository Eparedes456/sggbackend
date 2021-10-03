package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.models.Reserva;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	public List<Reserva> findAll(){
		return (List<Reserva>)reservaRepository.findAll();
	}
	
	public Reserva findById(Integer idReserva){
		
		return reservaRepository.findById(idReserva).orElse(null);
	}
	
	public void insert(Reserva reserva) {
		
		reservaRepository.save(reserva);
	}
	
	public void update(Reserva reserva) {
		reservaRepository.save(reserva);
	}
	
	public void delete(Reserva reserva) {
		reservaRepository.save(reserva);
	}
	
	public void deleteFisico(Reserva reserva) {
		reservaRepository.delete(reserva);
	}
	
	
}
