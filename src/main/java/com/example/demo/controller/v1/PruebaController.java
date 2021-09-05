package com.example.demo.controller.v1;

import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/tipo_documento",produces = "Application/json")
@CrossOrigin(origins = "*")
public class PruebaController {
	
	@ApiOperation(value = "Prueba")
	@GetMapping
	public ResponseEntity<?> index() {
		
		HashMap<String, Object> result = new HashMap<>();
		result.put("data", "Hola mundo");
		
		return new ResponseEntity<>(result,HttpStatus.OK);
		
	}
	
	
}
