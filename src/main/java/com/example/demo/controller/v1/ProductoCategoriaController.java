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

import com.example.demo.models.Categoria;
import com.example.demo.models.Producto_Categoria;
import com.example.demo.service.ProductoCategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/producto_categoria", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ProductoCategoriaController {

	@Autowired
	private ProductoCategoryService productoCategoriaService;
	
	
	@ApiOperation(value = "LISTA TODAS LOS PRODCUTOS QUE PERTENECEN A UNO O MAS CATEGORIAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(productoCategoriaService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UNA CATEGORIA POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idProductoCategoria}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idProductoCategoria") Integer idProductoCategoria
			){
				HashMap<String, Object> result = new HashMap<>();
				Producto_Categoria producto_categoria = productoCategoriaService.findById(idProductoCategoria);
				if(producto_categoria == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idProductoCategoria+" del producto categoria");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", producto_categoria);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN PRODUCTO CATEGORIA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertProductoCategoria(
			 @RequestBody Producto_Categoria productocategoria
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Producto_Categoria productoCategorias =	productoCategoriaService.findById(productocategoria.getIdProductoCategoria());
				
				if(productoCategorias != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+productocategoria.getIdProductoCategoria()+" del producto categoria");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				productocategoria.setEstado(true);
				productoCategoriaService.insert(productoCategorias);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", productoCategorias);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UN PRODUCTO CATEGORIA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateProductoCategoria(
			  @RequestBody Producto_Categoria productocategoria
			){
				HashMap<String, Object> result = new HashMap<>();
				Producto_Categoria productosCategorias = productoCategoriaService.findById(productocategoria.getIdProductoCategoria());
				
				if(productosCategorias == null) {
					result.put("success", false);
					result.put("message", "No existe el "+productocategoria.getIdProductoCategoria()+" de producto categoria");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				productoCategoriaService.update(productocategoria);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", productosCategorias);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteProductoCategoria(
	 @RequestBody Producto_Categoria productoCategoria
	){
		HashMap<String, Object> result = new HashMap<>();
		Producto_Categoria producto_Categorias = productoCategoriaService.findById(productoCategoria.getIdProductoCategoria());
		
		if(producto_Categorias == null) {
			result.put("success", false);
			result.put("message", "No existe el "+productoCategoria.getIdProductoCategoria()+" de producto categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		productoCategoria.setEstado(false);
		productoCategoriaService.delete(productoCategoria);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", producto_Categorias);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
	
	
	@DeleteMapping("/{iProductodCategoria}")
	public ResponseEntity<?>deleteFisicoProductoCategoria(
	 @PathVariable(value = "iProductodCategoria") Integer iProductodCategoria
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Producto_Categoria producto_Categorias = productoCategoriaService.findById(iProductodCategoria);
		
		if(producto_Categorias == null) {
			result.put("success", false);
			result.put("message", "No existe el "+iProductodCategoria+"  de producto categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		productoCategoriaService.deleteFisico(producto_Categorias);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", producto_Categorias);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
}
