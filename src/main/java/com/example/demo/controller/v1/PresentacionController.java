package com.example.demo.controller.v1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Clientes;
import com.example.demo.models.Presentacion;
import com.example.demo.service.ClientesService;
import com.example.demo.service.PresentacionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/api/v1/presentacion", produces = "Application/json")
@CrossOrigin(origins = "*")

public class PresentacionController {
	
	@Autowired
	private PresentacionService presentacionService;
	
	@ApiOperation(value = "LISTA TODAS LAS PRESENTACION", authorizations = {@Authorization(value = "apiKey")} )
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(presentacionService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UNA PRESENTACION POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idPresentacion}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idPresentacion") Integer idPresentacion
			){
				HashMap<String, Object> result = new HashMap<>();
				Presentacion presentaciones = presentacionService.findById(idPresentacion);
				if(presentaciones == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idPresentacion+" de la presentacion");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", presentaciones);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA PRESENTACION" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertPresentacion(
			 @RequestBody Presentacion presentacion
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Presentacion presentaciones =	presentacionService.findById(presentacion.getIdPresentacion());
				
				if(presentaciones != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+presentacion.getIdPresentacion()+" de la presentación");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				presentacion.setEstado(true);
				presentacionService.insert(presentacion);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", presentaciones);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LA PRESENTACION", authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateClientes(
			  @RequestBody Presentacion presentacion
			){
				HashMap<String, Object> result = new HashMap<>();
				Presentacion presentaciones = presentacionService.findById(presentacion.getIdPresentacion());
				
				if(presentaciones == null) {
					result.put("success", false);
					result.put("message", "No existe el "+presentacion.getIdPresentacion()+" de la presentación");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				presentacionService.update(presentacion);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", presentaciones);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Presentacion presentacion
	){
		HashMap<String, Object> result = new HashMap<>();
		Presentacion presentaciones = presentacionService.findById(presentacion.getIdPresentacion());
		
		if(presentaciones == null) {
			result.put("success", false);
			result.put("message", "No existe el "+presentacion.getIdPresentacion()+" de presentación");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		presentacion.setEstado(false);
		presentacionService.delete(presentacion);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", presentaciones);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

}
