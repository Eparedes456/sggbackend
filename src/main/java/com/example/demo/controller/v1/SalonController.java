package com.example.demo.controller.v1;

import java.util.HashMap;
import java.util.List;

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

import com.example.demo.models.Mesa;
import com.example.demo.models.Salon;
import com.example.demo.service.MesaService;
import com.example.demo.service.SalonService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/salon", produces = "Application/json")
@CrossOrigin(origins = "*")
public class SalonController {

	@Autowired
	private SalonService salonService;
	@Autowired
	private MesaService mesaService;
	
	@ApiOperation(value = "LISTA TODOS LOS SALONES")
	@GetMapping
	public ResponseEntity<?> findAllSalones() {
		return new ResponseEntity<>(salonService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA mesas QUE CORRSPONDEN A UN SALON BUSCANDO POR IDSALON")
	@GetMapping("/mesas/{idSalon}")
	public ResponseEntity<?>findBySalon(
		     @PathVariable(value = "idSalon") Integer idSalon
			){
				HashMap<String, Object> result = new HashMap<>();
				List<Mesa> mesas = mesaService.findMesaBySalon(idSalon);
				if(mesas==null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idSalon+" del tipo de documento");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", mesas);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value = "BUSCA UN SALON POR SU ID")
	@GetMapping("/{idSalon}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idSalon") Integer idSalon
			){
				HashMap<String, Object> result = new HashMap<>();
				Salon salon = salonService.findById(idSalon);
				if(salon==null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idSalon+" del tipo de documento");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", salon);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UN SALON")
	@PostMapping
	public ResponseEntity<?>insertSalon(
			 @RequestBody Salon salon
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Salon salons =	salonService.findById(salon.getIdSalon());
				
				if(salons != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+salon.getIdSalon()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				salon.setEstado(true);
				salonService.insert(salon);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", salon);
				return new ResponseEntity<>(result,HttpStatus.OK);
				// result.put("data", tmp)	
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UN SALON")
	@PutMapping
	public ResponseEntity<?> updateSalon(
			  @RequestBody Salon salon
			){
				HashMap<String, Object> result = new HashMap<>();
				Salon salons = salonService.findById(salon.getIdSalon());
				
				if(salons == null) {
					result.put("success", false);
					result.put("message", "No existe el "+salon.getIdSalon()+" del tipo Documento");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				salonService.update(salon);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", salon);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteSalon(
	 @RequestBody Salon salon
	){
		HashMap<String, Object> result = new HashMap<>();
		Salon salons =	salonService.findById(salon.getIdSalon());
		
		if(salons == null) {
			result.put("success", false);
			result.put("message", "No existe el "+salon.getIdSalon()+" de mesa");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		salon.setEstado(false);
		salonService.delete(salons);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", salons);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idSalon}")
	public ResponseEntity<?>deleteFisicoSalon(
	 @PathVariable(value = "idSalon") Integer idSalon
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Salon salons = salonService.findById(idSalon);
		
		if(salons == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idSalon+" del tipo Documento");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 salonService.deleteFisico(salons);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", salons);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}

