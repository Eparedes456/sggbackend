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

import com.example.demo.models.Empresa;
import com.example.demo.models.Extra;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.ExtraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/extra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ExtraController {
	
	@Autowired
	private ExtraService extraService;
	
	@ApiOperation(value = "LISTA TODOS LOS REGISTROS DE EXTRAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(extraService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UN EXTRA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idExtra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idExtra") Integer idExtra
			){
				HashMap<String, Object> result = new HashMap<>();
				Extra extra = extraService.findById(idExtra);
				if(extra == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idExtra+" del extra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", extra);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN EXTRA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertExtra(
			 @RequestBody Extra extra
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Extra extras =	extraService.findById(extra.getIdExtra());
				
				if(extras != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+extra.getIdExtra()+" de extra");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				extra.setEstado(true);
				extraService.insert(extra);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", extra);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DEL EXTRA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateExtra(
			  @RequestBody Extra extra
			){
				HashMap<String, Object> result = new HashMap<>();
				Extra extras = extraService.findById(extra.getIdExtra());
				
				if(extras == null) {
					result.put("success", false);
					result.put("message", "No existe el "+extra.getIdExtra()+" de extra");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				extraService.update(extras);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", extras);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteExtra(
	 @RequestBody Extra extra
	){
		HashMap<String, Object> result = new HashMap<>();
		Extra extras = extraService.findById(extra.getIdExtra());
		
		if(extras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+extra.getIdExtra()+" de extra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		extra.setEstado(false);
		extraService.delete(extra);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", extras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idExtra}")
	public ResponseEntity<?>deleteFisicoExtra(
	 @PathVariable(value = "idExtra") Integer idExtra
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Extra extras = extraService.findById(idExtra);
		
		if(extras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idExtra+" de extra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 extraService.deleteFisico(extras);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", extras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
