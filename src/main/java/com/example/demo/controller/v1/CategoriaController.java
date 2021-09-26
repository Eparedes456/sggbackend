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

import com.example.demo.models.Categoria;
import com.example.demo.models.Empresa;
import com.example.demo.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/categoria", produces = "Application/json")
@CrossOrigin(origins = "*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "LISTA TODAS LAS CATEGORIAS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(categoriaService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UNA CATEGORIA POR SU ID")
	@GetMapping("/{idCategoria}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idCategoria") Integer idCategoria
			){
				HashMap<String, Object> result = new HashMap<>();
				Categoria categoria = categoriaService.findById(idCategoria);
				if(categoria == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idCategoria+" de la categoria");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", categoria);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UNA CATEGORIA")
	@PostMapping
	public ResponseEntity<?>insertCategoria(
			 @RequestBody Categoria categoria
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Categoria categorias =	categoriaService.findById(categoria.getIdCategoria());
				
				if(categorias != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+categoria.getIdCategoria()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				categoria.setEstado(true);
				categoriaService.insert(categoria);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", categorias);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LA CATEGORIA")
	@PutMapping
	public ResponseEntity<?> updateCategoria(
			  @RequestBody Categoria categoria
			){
				HashMap<String, Object> result = new HashMap<>();
				Categoria categorias = categoriaService.findById(categoria.getIdCategoria());
				
				if(categorias == null) {
					result.put("success", false);
					result.put("message", "No existe el "+categoria.getIdCategoria()+" de la categoria");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				categoriaService.update(categoria);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", categorias);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Categoria categoria
	){
		HashMap<String, Object> result = new HashMap<>();
		Categoria categorias = categoriaService.findById(categoria.getIdCategoria());
		
		if(categorias == null) {
			result.put("success", false);
			result.put("message", "No existe el "+categoria.getIdCategoria()+" de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		categoria.setEstado(false);
		categoriaService.delete(categoria);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", categorias);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<?>deleteFisicoCategoria(
	 @PathVariable(value = "idCategoria") Integer idCategoria
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Categoria categorias = categoriaService.findById(idCategoria);
		
		if(categorias == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idCategoria+"  de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		categoriaService.deleteFisico(categorias);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", categorias);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
