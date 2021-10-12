package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Extra;
import com.example.demo.repository.ExtraRepository;

@Service
public class ExtraService {

	@Autowired
	private ExtraRepository extraRepository;
	
	public List<Extra> findAll() {
		   return (List<Extra>)extraRepository.findAll();
		}
	
	public Extra findById(Integer idExtra) {
		return extraRepository.findById(idExtra).orElse(null);
	}
	
	public void insert(Extra extra) {
			
		extraRepository.save(extra);
	}
	
	public void update(Extra extra) {
		extraRepository.save(extra);
	}
	
	public void delete(Extra extra) {
		extraRepository.save(extra);
	}
	
	public void deleteFisico(Extra extra) {
		extraRepository.delete(extra);
	}
	
	
}
