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
import com.example.demo.models.Proveedor;
import com.example.demo.service.ProveedorService;
import io.swagger.annotations.Authorization;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/proveedor", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	@ApiOperation(value = "LISTA TODOS LOS PROVEEDOR" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(proveedorService.findAll(),HttpStatus.OK);
	}
	@ApiOperation(value = "BUSCA UN PROVEEDOR POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idProveedor}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idProveedor") Integer idProveedor
			){
				HashMap<String, Object> result = new HashMap<>();
				Proveedor proveedors = proveedorService.findById(idProveedor);
				if(proveedors == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idProveedor+" del proveedor");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", proveedors);
				return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN PROVEEDOR" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertProveedor(
			
			@RequestBody Proveedor proveedor
	){
				HashMap<String, Object> result = new HashMap<>();
				
				Proveedor proveedors =	proveedorService.findById(proveedor.getIdProveedor());
				
				if(proveedors != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+ proveedor.getIdProveedor()+" del proveedor");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				proveedor.setEstado(true);
				proveedorService.insert(proveedor);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", proveedors);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
	}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DEL PROVEEDOR" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateProveedor(
			  @RequestBody Proveedor proveedor
			){
				HashMap<String, Object> result = new HashMap<>();
				Proveedor proveedors = proveedorService.findById(proveedor.getIdProveedor());
				
				if(proveedors == null) {
					result.put("success", false);
					result.put("message", "No existe el "+proveedor.getIdProveedor()+" del proveedor");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				proveedorService.update(proveedor);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", proveedors);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteProveedor(
	 @RequestBody Proveedor proveedor
	){
		HashMap<String, Object> result = new HashMap<>();
		Proveedor proveedors = proveedorService.findById(proveedor.getIdProveedor());
		
		if(proveedors == null) {
			result.put("success", false);
			result.put("message", "No existe el "+proveedor.getIdProveedor()+" del proveedor");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		proveedor.setEstado(false);
		proveedorService.delete(proveedor);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", proveedors);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idProveedor}")
	public ResponseEntity<?>deleteFisicoProveedor(
	 @PathVariable(value = "idProveedor") Integer idProveedor
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Proveedor proveedors = proveedorService.findById(idProveedor);
		
		if(proveedors == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idProveedor+"  del proveedor");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		proveedorService.deleteFisico(proveedors);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", proveedors);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
}
