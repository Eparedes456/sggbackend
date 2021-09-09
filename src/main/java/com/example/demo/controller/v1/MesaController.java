package com.example.demo.controller.v1;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MesaService;
import com.example.demo.models.Mesa;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/api/v1/mesa", produces = "Application/json")
@CrossOrigin(origins = "*")

public class MesaController {
	
	@Autowired
	private MesaService mesasService;
	
	/*@GetMapping
	public ResponseEntity<?> findAllPrueba(){
		
		HashMap<String, Object> result = new HashMap<>();
		
		result.put("mensaje","hola");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}*/
	
	@ApiOperation(value = "LISTA TODAS LAS MESAS")
	@GetMapping
	public ResponseEntity<?> findAllMesas() {
		return new ResponseEntity<>(mesasService.findAll(),HttpStatus.OK);
	}

}
