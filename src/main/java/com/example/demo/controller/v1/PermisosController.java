package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Authorization;


import com.example.demo.service.PermisosService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/permisos", produces = "Application/json")
@CrossOrigin(origins = "*")
public class PermisosController {

	@Autowired
	private PermisosService permisosService;
	
	
	@ApiOperation(value = "LISTAR TODOS LOS PERMISOS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAllPermisos() {
		return new ResponseEntity<>(permisosService.findAll(),HttpStatus.OK);
	}
	
	
	
	
}
