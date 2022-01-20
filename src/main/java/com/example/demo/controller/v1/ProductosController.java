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

import com.example.demo.models.Clientes;
import com.example.demo.models.Productos;
import com.example.demo.service.ProductoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/productos", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ProductosController {
	
	@Autowired
	private ProductoService productoService;
	
	@ApiOperation(value = "LISTA TODOS LOS PRODUCTOS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(productoService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UN PRODUCTO POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idProducto}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idProducto") Integer idProducto
			){
				HashMap<String, Object> result = new HashMap<>();
				Productos productos = productoService.findById(idProducto);
				if(productos == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idProducto+" de la categoria");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", productos);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UN PRODUCTO" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertProducto(
			 @RequestBody Productos producto
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Productos productos =	productoService.findById(producto.getIdProducto());
				
				if(productos != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+producto.getIdProducto()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				producto.setEstado(true);
				productoService.insert(producto);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", productos);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LOS PRODUCTOS" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateProductos(
			  @RequestBody Productos producto
			){
				HashMap<String, Object> result = new HashMap<>();
				Productos productos = productoService.findById(producto.getIdProducto());
				
				if(productos == null) {
					result.put("success", false);
					result.put("message", "No existe el "+producto.getIdProducto()+" de la categoria");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				productoService.update(producto);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", productos);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Productos producto
	){
		HashMap<String, Object> result = new HashMap<>();
		Productos productos = productoService.findById(producto.getIdProducto());
		
		if(productos == null) {
			result.put("success", false);
			result.put("message", "No existe el "+producto.getIdProducto()+" de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		producto.setEstado(false);
		productoService.delete(producto);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", productos);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idProducto}")
	public ResponseEntity<?>deleteFisicoCategoria(
	 @PathVariable(value = "idCliente") Integer idProducto
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Productos productos = productoService.findById(idProducto);
		
		if(productos == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idProducto+"  de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		productoService.deleteFisico(productos);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", productos);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
