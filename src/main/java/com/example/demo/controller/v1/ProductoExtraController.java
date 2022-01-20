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

import com.example.demo.models.Producto_Categoria;
import com.example.demo.models.Producto_extra;
import com.example.demo.service.ProductoCategoryService;
import com.example.demo.service.ProductoExtraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/producto_extra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ProductoExtraController {

	@Autowired
	private ProductoExtraService productoExtraService;
	
	
	@ApiOperation(value = "LISTA TODOS LOS EXTRAS PERTENECIENTE A UNO O VARIOS PRODUCTOS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(productoExtraService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UNA EXTRA POR PRODUCTO POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idProductoExtra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idProductoExtra") Integer idProductoExtra
			){
				HashMap<String, Object> result = new HashMap<>();
				Producto_extra producto_extra = productoExtraService.findById(idProductoExtra);
				if(producto_extra == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idProductoExtra+" del producto extra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", producto_extra);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN PRODUCTO EXTRA" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertProductoCategoria(
			 @RequestBody Producto_extra productoExtra
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Producto_extra productoExtras =	productoExtraService.findById(productoExtra.getIdProductoExtra());
				
				if(productoExtras != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+productoExtra.getIdProductoExtra()+" del producto extra");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				//productoExtra.setEstado(true);
				productoExtraService.insert(productoExtra);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", productoExtras);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UN PRODUCTO CATEGORIA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateProductoCategoria(
			  @RequestBody Producto_extra productoExtra
			){
				HashMap<String, Object> result = new HashMap<>();
				Producto_extra productosExtras = productoExtraService.findById(productoExtra.getIdProductoExtra());
				
				if(productosExtras == null) {
					result.put("success", false);
					result.put("message", "No existe el "+productoExtra.getIdProductoExtra()+" de producto extra");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				productoExtraService.update(productoExtra);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", productosExtras);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteProductoExtra(
	 @RequestBody Producto_extra productoExtra
	){
		HashMap<String, Object> result = new HashMap<>();
		Producto_extra producto_Extras = productoExtraService.findById(productoExtra.getIdProductoExtra());
		
		if(producto_Extras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+productoExtra.getIdProductoExtra()+" de producto extra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		//productoCategoria.setEstado(false);
		productoExtraService.delete(productoExtra);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", producto_Extras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{iProductoExtra}")
	public ResponseEntity<?>deleteFisicoProductoExtra(
	 @PathVariable(value = "iProductoExtra") Integer iProductoExtra
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Producto_extra producto_Extras = productoExtraService.findById(iProductoExtra);
		
		if(producto_Extras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+iProductoExtra+"  de producto extra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		productoExtraService.deleteFisico(producto_Extras);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", producto_Extras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
	
	
}
