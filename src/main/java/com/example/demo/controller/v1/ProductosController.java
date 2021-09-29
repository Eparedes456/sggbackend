package com.example.demo.controller.v1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@ApiOperation(value = "LISTA TODOS LOS PRODUCTOS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(productoService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UN PRODUCTO POR SU ID")
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

}
