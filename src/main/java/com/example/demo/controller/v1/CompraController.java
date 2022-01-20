package com.example.demo.controller.v1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.models.Compra;
import com.example.demo.service.CompraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/compra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@ApiOperation(value = "LISTA TODAS LAS COMPRAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(compraService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UNA COMPRA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idCompra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idCompra") Integer idCompra
			){
				HashMap<String, Object> result = new HashMap<>();
				Compra compras = compraService.findById(idCompra);
				if(compras == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idCompra+" de la compra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", compras);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UNA COMPRA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertCompra(
			
			@RequestBody Compra compra
	){
				HashMap<String, Object> result = new HashMap<>();
				
				Compra compras =	compraService.findById(compra.getIdCompra());
				
				if(compras != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+compra.getIdCompra()+" de la compra");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				compra.setEstado(true);
				compraService.insert(compra);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", compras);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
	}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UNA COMPRA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateCompras(
			  @RequestBody Compra compra
			){
				HashMap<String, Object> result = new HashMap<>();
				Compra compras = compraService.findById(compra.getIdCompra());
				
				if(compras == null) {
					result.put("success", false);
					result.put("message", "No existe el "+compra.getIdCompra()+" de empleado");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				compraService.update(compra);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", compras);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO",  authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCompra(
	 @RequestBody Compra compra
	){
		HashMap<String, Object> result = new HashMap<>();
		Compra compras = compraService.findById(compra.getIdCompra());
		
		if(compras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+compra.getIdCompra()+" de la compra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		compra.setEstado(false);
		compraService.delete(compra);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", compras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idCompra}")
	public ResponseEntity<?>deleteFisicoCompra(
	 @PathVariable(value = "idCompra") Integer idCompra
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Compra compras = compraService.findById(idCompra);
		
		if(compras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idCompra+"  de la compra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		compraService.deleteFisico(compras);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", compras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
