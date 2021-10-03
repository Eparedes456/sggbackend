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

import com.example.demo.models.Mesa;
import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/pedido", produces = "Application/json")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@ApiOperation(value = "LISTA TODAS LAS MESAS")
	@GetMapping
	public ResponseEntity<?> findAllPedido() {
		return new ResponseEntity<>(pedidoService.findAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "BUSCA UN PEDIDO POR SU ID")
	@GetMapping("/{idPedido}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idPedido") Integer idPedido
			){
				HashMap<String, Object> result = new HashMap<>();
				Pedido pedido = pedidoService.findById(idPedido);
				if(pedido==null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idPedido+" del pedido");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", pedido);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	@ApiOperation(value="CREAR Y GUARDAR UN PEDIDO")
	@PostMapping
	public ResponseEntity<?>insertPedido(
			 @RequestBody Pedido pedido
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Pedido pedidos = pedidoService.findById(
						pedido.getIdPedido() 
						);
				
				if(pedidos != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+pedido.getIdPedido()+" del pedido");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				pedido.setEstado(true);
				pedidoService.insert(pedido);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", pedidos);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DE UN PEDIDO")
	@PutMapping
	public ResponseEntity<?> updateTipoPedido(
			  @RequestBody Pedido pedido
			){
				HashMap<String, Object> result = new HashMap<>();
				Pedido pedidos = pedidoService.findById(pedido.getIdPedido());
				
				if(pedidos == null) {
					result.put("success", false);
					result.put("message", "No existe el "+pedido.getIdPedido()+" del pedido");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				pedidoService.update(pedido);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", pedidos);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteTipoDocumento(
	 @RequestBody Pedido pedido
	){
		HashMap<String, Object> result = new HashMap<>();
		Pedido pedidos = pedidoService.findById(pedido.getIdPedido());
		
		if(pedidos == null) {
			result.put("success", false);
			result.put("message", "No existe el "+pedido.getIdPedido()+" de pedido");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		pedido.setEstado(false);
		pedidoService.delete(pedidos);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", pedidos);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idPedido}")
	public ResponseEntity<?>deleteFisicoPedido(
	 @PathVariable(value = "idPedido") Integer idPedido
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Pedido pedidos = pedidoService.findById(idPedido);
		
		if(pedidos == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idPedido+" del tipo Documento");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		 pedidoService.deleteFisico(pedidos);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", pedidos);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
