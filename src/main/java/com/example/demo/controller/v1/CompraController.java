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

import com.example.demo.models.Categoria;
import com.example.demo.models.Compra;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.CompraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/compra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@ApiOperation(value = "LISTA TODAS LAS COMPRAS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(compraService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UNA COMPRA POR SU ID")
	@GetMapping("/{idCompra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idCompra") Integer idCompra
			){
				HashMap<String, Object> result = new HashMap<>();
				Compra compras = compraService.findById(idCompra);
				if(compras == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idCompra+" de la compra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", compras);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}

}
