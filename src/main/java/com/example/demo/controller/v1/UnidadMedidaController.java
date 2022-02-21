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
import com.example.demo.models.UnidadMedida;
import com.example.demo.service.ClientesService;
import com.example.demo.service.UnidadMedidaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/api/v1/unidadmedida", produces = "Application/json")
@CrossOrigin(origins = "*")
public class UnidadMedidaController {
	
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	
	@ApiOperation(value = "LISTA TODAS LAS UNIDADES DE MEDIDA", authorizations = {@Authorization(value = "apiKey")} )
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(unidadMedidaService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UNA UNIDAD DE MEDIDA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idUnidadMedida}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idUnidadMedida") Integer idUnidadMedida
			){
				HashMap<String, Object> result = new HashMap<>();
				UnidadMedida unidadMedida = unidadMedidaService.findById(idUnidadMedida);
				if(unidadMedida == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idUnidadMedida+" de la categoria");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", unidadMedida);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA UNIDAD DE MEDIDA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertUnidadMedida(
			 @RequestBody UnidadMedida unidadMedida
			){
				HashMap<String, Object> result = new HashMap<>();
				
				UnidadMedida clientes =	unidadMedidaService.findById(unidadMedida.getIdUnidadMedida());
				
				if(clientes != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+unidadMedida.getIdUnidadMedida()+" unidad de medida");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				unidadMedida.setEstado(true);
				unidadMedidaService.insert(unidadMedida);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", clientes);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LAS UNIDADES DE MEDIDA", authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateUnidadMedida(
			  @RequestBody UnidadMedida unidadMedida
			){
				HashMap<String, Object> result = new HashMap<>();
				UnidadMedida unidadMedidas = unidadMedidaService.findById(unidadMedida.getIdUnidadMedida());
				
				if(unidadMedidas == null) {
					result.put("success", false);
					result.put("message", "No existe el "+unidadMedida.getIdUnidadMedida()+" de la unidad de medida");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				unidadMedidaService.update(unidadMedida);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", unidadMedidas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteUnidadMedida(
	 @RequestBody UnidadMedida unidadMedida
	){
		HashMap<String, Object> result = new HashMap<>();
		UnidadMedida unidadMedidas = unidadMedidaService.findById(unidadMedida.getIdUnidadMedida());
		
		if(unidadMedidas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+unidadMedida.getIdUnidadMedida()+" de unidad de medida");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		unidadMedida.setEstado(false);
		unidadMedidaService.delete(unidadMedida);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", unidadMedidas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

}
