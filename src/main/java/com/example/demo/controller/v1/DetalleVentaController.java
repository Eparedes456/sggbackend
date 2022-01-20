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

import com.example.demo.models.DetalleCompra;
import com.example.demo.models.DetalleVenta;
import com.example.demo.service.DetalleVentaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/detalleventa", produces = "Application/json")
@CrossOrigin(origins = "*")
public class DetalleVentaController {
	
	@Autowired
	private DetalleVentaService detalleVentaService;
	
	@ApiOperation(value = "LISTA TODOS LOS DETALLES VENTAS" , authorizations = {@Authorization(value = "apiKey")} )
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(detalleVentaService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN DETALLE VENTAS" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertDetalleCompra(
			
			@RequestBody DetalleVenta detalleVenta
	){
				HashMap<String, Object> result = new HashMap<>();
				
				DetalleVenta detalleVentas =	detalleVentaService.findById(detalleVenta.getIdDetalleVenta());
				
				if(detalleVentas != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+detalleVenta.getIdDetalleVenta()+" del detalle de la venta");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				detalleVenta.setEstado(true);
				detalleVentaService.insert(detalleVenta);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", detalleVentas);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
	}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DEL DETALLE DE LA VENTA" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateDeleteCompras(
			  @RequestBody DetalleVenta detalleVenta
			){
				HashMap<String, Object> result = new HashMap<>();
				DetalleVenta detalleVentas = detalleVentaService.findById(detalleVenta.getIdDetalleVenta());
				
				if(detalleVentas == null) {
					result.put("success", false);
					result.put("message", "No existe el "+detalleVenta.getIdDetalleVenta()+" del detalle de la venta");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				detalleVentaService.update(detalleVenta);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", detalleVentas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteDetalleVenta(
	 @RequestBody DetalleVenta detalleVenta
	){
		HashMap<String, Object> result = new HashMap<>();
		DetalleVenta detalleVentas = detalleVentaService.findById(detalleVenta.getIdDetalleVenta());
		
		if(detalleVentas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+detalleVenta.getIdDetalleVenta()+" del detalle de la venta");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		detalleVenta.setEstado(false);
		detalleVentaService.delete(detalleVenta);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", detalleVentas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idDetalleVenta}")
	public ResponseEntity<?>deleteFisicoDetalleCompra(
	 @PathVariable(value = "idDetalleVenta") Integer idDetalleVenta
	){
		
		HashMap<String, Object> result = new HashMap<>();
		DetalleVenta detalleVentas = detalleVentaService.findById(idDetalleVenta);
		
		if(detalleVentas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idDetalleVenta+"  del Detalle de la venta");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		detalleVentaService.deleteFisico(detalleVentas);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", detalleVentas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

}
