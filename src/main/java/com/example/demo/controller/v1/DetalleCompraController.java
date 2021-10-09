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

import com.example.demo.models.Compra;
import com.example.demo.models.DetalleCompra;
import com.example.demo.service.CompraService;
import com.example.demo.service.DetalleCompraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/detallecompra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class DetalleCompraController {

	@Autowired
	private DetalleCompraService detalleCompraService;
	
	@ApiOperation(value = "LISTA TODOS LOS DETALLES COMPRAS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(detalleCompraService.findAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "BUSCA UN DETALLE COMPRA POR SU ID")
	@GetMapping("/{idDetalleCompra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idDetalleCompra") Integer idDetalleCompra
			){
				HashMap<String, Object> result = new HashMap<>();
				DetalleCompra detalleCompras = detalleCompraService.findById(idDetalleCompra);
				if(detalleCompras == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idDetalleCompra+" de la compra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", detalleCompras);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN DETALLE COMPRA")
	@PostMapping
	public ResponseEntity<?>insertDetalleCompra(
			
			@RequestBody DetalleCompra detalleCompra
	){
				HashMap<String, Object> result = new HashMap<>();
				
				DetalleCompra detalleCompras =	detalleCompraService.findById(detalleCompra.getIdDetalleCompra());
				
				if(detalleCompras != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+detalleCompra.getIdDetalleCompra()+" del detalle de la compra");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				detalleCompra.setEstado(true);
				detalleCompraService.insert(detalleCompra);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", detalleCompras);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
	}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DEL DETALLE DE LA COMPRA")
	@PutMapping
	public ResponseEntity<?> updateDeleteCompras(
			  @RequestBody DetalleCompra detalleCompra
			){
				HashMap<String, Object> result = new HashMap<>();
				DetalleCompra detalleCompras = detalleCompraService.findById(detalleCompra.getIdDetalleCompra());
				
				if(detalleCompras == null) {
					result.put("success", false);
					result.put("message", "No existe el "+detalleCompra.getIdDetalleCompra()+" del detalle de la compra");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				detalleCompraService.update(detalleCompra);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", detalleCompras);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteDetalleCompra(
	 @RequestBody DetalleCompra detalleCompra
	){
		HashMap<String, Object> result = new HashMap<>();
		DetalleCompra detalleCompras = detalleCompraService.findById(detalleCompra.getIdDetalleCompra());
		
		if(detalleCompras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+detalleCompra.getIdDetalleCompra()+" del detalle de la compra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		detalleCompra.setEstado(false);
		detalleCompraService.delete(detalleCompra);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", detalleCompras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{idDetalleCompra}")
	public ResponseEntity<?>deleteFisicoDetalleCompra(
	 @PathVariable(value = "idDetalleCompra") Integer idDetalleCompra
	){
		
		HashMap<String, Object> result = new HashMap<>();
		DetalleCompra detalleCompras = detalleCompraService.findById(idDetalleCompra);
		
		if(detalleCompras == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idDetalleCompra+"  del Detalle de la compra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		detalleCompraService.deleteFisico(detalleCompras);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", detalleCompras);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
