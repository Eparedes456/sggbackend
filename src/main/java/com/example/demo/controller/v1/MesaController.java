package com.example.demo.controller.v1;

import java.util.HashMap;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Authorization;

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
	
	
	
	@ApiOperation(value = "LISTA TODAS LAS MESAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAllMesas() {
		return new ResponseEntity<>(mesasService.findAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "BUSCA UNA MESA POR SU ID")
	@GetMapping("/{idMesa}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idMesa") Integer idMesa
			){
				HashMap<String, Object> result = new HashMap<>();
				Mesa mesa = mesasService.findById(idMesa);
				if(mesa==null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idMesa+" del tipo de documento");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", mesa);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UNA MESA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertMesa(
			 @RequestBody Mesa mesa
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Mesa table = mesasService.findById(
						mesa.getIdMesa() 
						);
				
				if(table != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+mesa.getIdMesa()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				mesa.setEstado(true);
				mesasService.insert(mesa);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", table);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UNA MESA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateTipoDocumento(
			  @RequestBody Mesa mesa
			){
				HashMap<String, Object> result = new HashMap<>();
				Mesa table = mesasService.findById(mesa.getIdMesa());
				
				if(table == null) {
					result.put("success", false);
					result.put("message", "No existe el "+mesa.getIdMesa()+" del tipo Documento");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				mesasService.update(mesa);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", table);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteTipoDocumento(
	 @RequestBody Mesa mesa
	){
		HashMap<String, Object> result = new HashMap<>();
		Mesa table = mesasService.findById(mesa.getIdMesa());
		
		if(table == null) {
			result.put("success", false);
			result.put("message", "No existe el "+mesa.getIdMesa()+" de mesa");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		mesa.setEstado(false);
		mesasService.delete(table);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", table);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idMesa}")
	public ResponseEntity<?>deleteFisicoMesa(
	 @PathVariable(value = "idMesa") Integer idMesa
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Mesa table = mesasService.findById(idMesa);
		
		if(table == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idMesa+" del tipo Documento");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 mesasService.deleteFisico(table);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", table);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	

}
