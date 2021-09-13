package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Mesa;
import com.example.demo.models.Salon;
import com.example.demo.repository.SalonRepository;


@Service
public class SalonService {

	@Autowired
	private SalonRepository salonRepository;
	
	public List<Salon> findAll() {
		   return (List<Salon>)salonRepository.findAll();
		}
	
	public Salon findById(Integer idSalon) {
		return salonRepository.findById(idSalon).orElse(null);
	}
	
	public void insert(Salon salon) {
		
		salonRepository.save(salon);
	}
	
	public void update(Salon salon) {
		salonRepository.save(salon);
	}
	
	public void delete(Salon salon) {
		salonRepository.save(salon);
	}
	
	public void deleteFisico(Salon salon) {
		salonRepository.delete(salon);
	}
	
	
}
