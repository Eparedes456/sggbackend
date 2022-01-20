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
import com.example.demo.service.EmpresaService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/v1/empresa", produces = "Application/json")
@CrossOrigin(origins = "*")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@ApiOperation(value = "LISTA TODAS LAS EMPRESAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(empresaService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UNA EMPRESA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idEmpresa}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idEmpresa") Integer idEmpresa
			){
				HashMap<String, Object> result = new HashMap<>();
				Empresa empresa = empresaService.findById(idEmpresa);
				if(empresa == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idEmpresa+" del tipo de documento");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", empresa);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA EMPRESA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertEmpresa(
			 @RequestBody Empresa empresa
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Empresa empresas =	empresaService.findById(empresa.getIdEmpresa());
				
				if(empresas != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+empresa.getIdEmpresa()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				empresa.setEstado(true);
				empresaService.insert(empresa);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", empresa);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LA EMPRESA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateEmpresa(
			  @RequestBody Empresa empresa
			){
				HashMap<String, Object> result = new HashMap<>();
				Empresa empresas = empresaService.findById(empresa.getIdEmpresa());
				
				if(empresas == null) {
					result.put("success", false);
					result.put("message", "No existe el "+empresa.getIdEmpresa()+" del tipo Documento");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				empresaService.update(empresa);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", empresas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deletEmpresa(
	 @RequestBody Empresa empresa
	){
		HashMap<String, Object> result = new HashMap<>();
		Empresa empresas = empresaService.findById(empresa.getIdEmpresa());
		
		if(empresas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+empresa.getIdEmpresa()+" de mesa");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		empresa.setEstado(false);
		empresaService.delete(empresa);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", empresas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idEmpresa}")
	public ResponseEntity<?>deleteFisicoEmpresa(
	 @PathVariable(value = "idEmpresa") Integer idEmpresa
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Empresa empresas = empresaService.findById(idEmpresa);
		
		if(empresas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idEmpresa+" del tipo Documento");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 empresaService.deleteFisico(empresas);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", empresas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

}
