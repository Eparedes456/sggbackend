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
import com.example.demo.models.Venta;
import com.example.demo.service.CompraService;
import com.example.demo.service.VentaService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(value = "/api/v1/venta", produces = "Application/json")
@CrossOrigin(origins = "*")
public class VentaController {
	
	@Autowired	
	private VentaService ventaService;
	
	@ApiOperation(value = "LISTA TODAS LAS COMPRAS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(ventaService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA VENTA")
	@PostMapping
	public ResponseEntity<?>insertVenta(
			
			@RequestBody Venta venta
	){
				HashMap<String, Object> result = new HashMap<>();
				
				Venta ventas =	ventaService.findById(venta.getIdVenta());
				
				if(ventas != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+venta.getIdVenta()+" de la venta");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				venta.setEstado(true);
				ventaService.insert(venta);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", ventas);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
	}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UNA VENTA")
	@PutMapping
	public ResponseEntity<?> updateVenta(
			  @RequestBody Venta venta
			){
				HashMap<String, Object> result = new HashMap<>();
				Venta ventas = ventaService.findById(venta.getIdVenta());
				
				if(ventas == null) {
					result.put("success", false);
					result.put("message", "No existe el "+venta.getIdVenta()+" de venta");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				ventaService.update(venta);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", ventas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteVenta(
	 @RequestBody Venta venta
	){
		HashMap<String, Object> result = new HashMap<>();
		Venta ventas = ventaService.findById(venta.getIdVenta());
		
		if(ventas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+venta.getIdVenta()+" de venta");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		venta.setEstado(false);
		ventaService.delete(venta);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", ventas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idVenta}")
	public ResponseEntity<?>deleteFisicoCompra(
	 @PathVariable(value = "idVenta") Integer idVenta
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Venta ventas = ventaService.findById(idVenta);
		
		if(ventas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idVenta+"  de la compra");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		ventaService.deleteFisico(ventas);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", ventas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
}
