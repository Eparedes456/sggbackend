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

import com.example.demo.models.Empleado;
import com.example.demo.models.Productos;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.ProductoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/empleado", produces = "Application/json")
@CrossOrigin(origins = "*")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@ApiOperation(value = "LISTA TODOS LOS PRODUCTOS" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(empleadoService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UN EMPLEADO POR SU ID" , authorizations = {@Authorization(value = "apiKey")})
	@GetMapping("/{idEmpleado}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idEmpleado") Integer idEmpleado
			){
				HashMap<String, Object> result = new HashMap<>();
				Empleado empleados = empleadoService.findById(idEmpleado);
				if(empleados == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idEmpleado+" de empleado");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", empleados);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN EMPLEADO" , authorizations = {@Authorization(value = "apiKey")})
	@PostMapping
	public ResponseEntity<?>insertEmpleado(
			 @RequestBody Empleado empleado
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Empleado empleados =	empleadoService.findById(empleado.getIdEmpleado());
				
				if(empleados != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+empleado.getIdEmpleado()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				empleado.setEstado(true);
				empleadoService.insert(empleado);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", empleados);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LOS EMPLEADOS" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping
	public ResponseEntity<?> updateProductos(
			  @RequestBody Empleado empleado
			){
				HashMap<String, Object> result = new HashMap<>();
				Empleado empleados = empleadoService.findById(empleado.getIdEmpleado());
				
				if(empleados == null) {
					result.put("success", false);
					result.put("message", "No existe el "+empleado.getIdEmpleado()+" de empleado");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				empleadoService.update(empleado);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", empleados);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO" , authorizations = {@Authorization(value = "apiKey")})
	@PutMapping("/delete")
	public ResponseEntity<?> deleteEmpleado(
	 @RequestBody Empleado empleado
	){
		HashMap<String, Object> result = new HashMap<>();
		Empleado empleados = empleadoService.findById(empleado.getIdEmpleado());
		
		if(empleados == null) {
			result.put("success", false);
			result.put("message", "No existe el "+empleado.getIdEmpleado()+" de empleado");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		empleado.setEstado(false);
		empleadoService.delete(empleado);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", empleados);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/{idEmpleado}")
	public ResponseEntity<?>deleteFisicoEmpleado(
	 @PathVariable(value = "idEmpleado") Integer idEmpleado
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Empleado empleados = empleadoService.findById(idEmpleado);
		
		if(empleados == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idEmpleado+"  de empleado");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		empleadoService.deleteFisico(empleados);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", empleados);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
