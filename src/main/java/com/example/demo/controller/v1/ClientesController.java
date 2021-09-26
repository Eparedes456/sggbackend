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

import com.example.demo.models.Categoria;
import com.example.demo.models.Clientes;
import com.example.demo.service.ClientesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/clientes", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ClientesController {
	
	@Autowired
	private ClientesService clienteService;
	
	@ApiOperation(value = "LISTA TODOS LOS CLIENTES")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(clienteService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UN CLIENTE POR SU ID")
	@GetMapping("/{idClinica}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idClinica") Integer idCliente
			){
				HashMap<String, Object> result = new HashMap<>();
				Clientes clientes = clienteService.findById(idCliente);
				if(clientes == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idCliente+" de la categoria");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", clientes);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UN CLIENTE")
	@PostMapping
	public ResponseEntity<?>insertCliente(
			 @RequestBody Clientes cliente
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Clientes clientes =	clienteService.findById(cliente.getIdCliente());
				
				if(clientes != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+cliente.getIdCliente()+" del tipo de documento");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				cliente.setEstado(true);
				clienteService.insert(cliente);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", clientes);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE LOS CLIENTES")
	@PutMapping
	public ResponseEntity<?> updateClientes(
			  @RequestBody Clientes cliente
			){
				HashMap<String, Object> result = new HashMap<>();
				Clientes clientes = clienteService.findById(cliente.getIdCliente());
				
				if(clientes == null) {
					result.put("success", false);
					result.put("message", "No existe el "+cliente.getIdCliente()+" de la categoria");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				clienteService.update(cliente);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", clientes);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteCategoria(
	 @RequestBody Clientes cliente
	){
		HashMap<String, Object> result = new HashMap<>();
		Clientes clientes = clienteService.findById(cliente.getIdCliente());
		
		if(clientes == null) {
			result.put("success", false);
			result.put("message", "No existe el "+cliente.getIdCliente()+" de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		cliente.setEstado(false);
		clienteService.delete(cliente);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", clientes);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?>deleteFisicoCategoria(
	 @PathVariable(value = "idCliente") Integer idCliente
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Clientes clientes = clienteService.findById(idCliente);
		
		if(clientes == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idCliente+"  de categoria");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		clienteService.deleteFisico(clientes);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", clientes);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
