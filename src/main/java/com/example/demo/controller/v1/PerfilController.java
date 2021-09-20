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

import com.example.demo.models.Mesa;
import com.example.demo.models.Perfil;
import com.example.demo.service.PerfilService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/perfil", produces = "Application/json")
@CrossOrigin(origins = "*")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@ApiOperation(value = "LISTAR TODOS LOS PERFILES")
	@GetMapping
	public ResponseEntity<?> findAllPerfiles() {
		return new ResponseEntity<>(perfilService.findAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "BUSCA UN PERFIL POR SU ID")
	@GetMapping("/{idPerfil}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idPerfil") Integer idPerfil
			){
				HashMap<String, Object> result = new HashMap<>();
				Perfil perfil = perfilService.findById(idPerfil);
				if(perfil==null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idPerfil+" del tipo de documento");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", perfil);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UN PERFIL")
	@PostMapping
	public ResponseEntity<?>insertPerfil(
			 @RequestBody Perfil perfil
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Perfil profile = perfilService.findById(
						perfil.getIdPerfil() 
						);
				
				if(profile != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+perfil.getIdPerfil()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				perfil.setEstado(true);
				perfilService.insert(perfil);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", profile);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UN PERFIL")
	@PutMapping
	public ResponseEntity<?> updatePerfil(
			  @RequestBody Perfil perfil
			){
				HashMap<String, Object> result = new HashMap<>();
				Perfil profile = perfilService.findById(perfil.getIdPerfil());
				
				if(profile == null) {
					result.put("success", false);
					result.put("message", "No existe el "+perfil.getIdPerfil()+" del tipo Documento");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				perfilService.update(perfil);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", profile);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deletePerfil(
	 @RequestBody Perfil perfil
	){
		HashMap<String, Object> result = new HashMap<>();
		Perfil profile = perfilService.findById(perfil.getIdPerfil());
		
		if(profile == null) {
			result.put("success", false);
			result.put("message", "No existe el "+perfil.getIdPerfil()+" de mesa");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		perfil.setEstado(false);
		perfilService.delete(perfil);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", profile);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPerfil}")
	public ResponseEntity<?>deleteFisicoPerfil(
	 @PathVariable(value = "idPerfil") Integer idPerfil
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Perfil profile = perfilService.findById(idPerfil);
		
		if(profile == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idPerfil+" del tipo Documento");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 perfilService.deleteFisico(profile);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", profile);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
