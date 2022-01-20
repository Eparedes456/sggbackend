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
import com.example.demo.models.Reserva;
import com.example.demo.service.ReservaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/reserva", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@ApiOperation(value = "LISTA TODAS LAS RESERVAS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(reservaService.findAll(),HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "BUSCA UNA RESERVA POR EL ID")
	@GetMapping("/{idReserva}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idReserva") Integer idReserva
			){
				HashMap<String, Object> result = new HashMap<>();
				Reserva reservas = reservaService.findById(idReserva);
				if(reservas == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idReserva+" de reserva");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", reservas);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UNA RESERVA")
	@PostMapping
	public ResponseEntity<?>insertReserva(
			 @RequestBody Reserva reserva
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Reserva reservas =	reservaService.findById(reserva.getIdReserva());
				
				if(reservas != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+reserva.getIdReserva()+" de Reserva");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				reserva.setEstado(true);
				reservaService.insert(reserva);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", reservas);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LA RESERVA")
	@PutMapping
	public ResponseEntity<?> update(
			  @RequestBody Reserva reserva
			){
				HashMap<String, Object> result = new HashMap<>();
				Reserva reservas = reservaService.findById(reserva.getIdReserva());
				
				if(reservas == null) {
					result.put("success", false);
					result.put("message", "No existe el "+reserva.getIdReserva()+" de reserva");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				reservaService.update(reserva);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", reservas);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Reserva reserva
	){
		HashMap<String, Object> result = new HashMap<>();
		Reserva reservas = reservaService.findById(reserva.getIdReserva());
		
		if(reservas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+reserva.getIdReserva()+" de reserva");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		reserva.setEstado(false);
		reservaService.delete(reserva);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", reservas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idReserva}")
	public ResponseEntity<?>deleteFisicoReserva(
	 @PathVariable(value = "idReserva") Integer idReserva
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Reserva reservas = reservaService.findById(idReserva);
		
		if(reservas == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idReserva+"  de reserva");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		reservaService.deleteFisico(reservas);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", reservas);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
