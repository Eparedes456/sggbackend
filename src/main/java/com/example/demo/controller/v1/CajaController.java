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

import com.example.demo.models.Caja;
import com.example.demo.models.Clientes;
import com.example.demo.service.CajaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/api/v1/cajas", produces = "Application/json")
@CrossOrigin(origins = "*")
public class CajaController {
	
	
	@Autowired
	private CajaService cajaService;
	
	@ApiOperation(value = "LISTA TODAS LAS CAJAS", authorizations = {@Authorization(value = "apiKey")} )
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(cajaService.findAll(),HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value = "BUSCA UNA CAJA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idCaja}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idCaja") Integer idCaja
			){
				HashMap<String, Object> result = new HashMap<>();
				Caja cajas = cajaService.findById(idCaja);
				if(cajas == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idCaja+"  de caja");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", cajas);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA CAJA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertCaja(
			 @RequestBody Caja caja
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Caja cajas =	cajaService.findById(caja.getIdCaja());
				
				if(cajas != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+caja.getIdCaja()+" de la caja");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				caja.setEstado(true);
				cajaService.insert(caja);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", cajas);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LOS CLIENTES", authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateCaja(
			  @RequestBody Caja caja
			){
				HashMap<String, Object> result = new HashMap<>();
				Caja cajas = cajaService.findById(caja.getIdCaja());
				
				if(cajas == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+caja.getIdCaja()+" de caja ");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				cajaService.update(caja);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", cajas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Caja caja
	){
		HashMap<String, Object> result = new HashMap<>();
		Caja cajas = cajaService.findById(caja.getIdCaja());
		
		if(cajas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+caja.getIdCaja()+" de caja");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		caja.setEstado(false);
		cajaService.delete(caja);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", cajas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
