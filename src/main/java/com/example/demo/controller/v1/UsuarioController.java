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
import com.example.demo.models.Usuarios;
import com.example.demo.service.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/usuario", produces = "Application/json")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuariosService;
	
	@ApiOperation(value = "LISTA TODOS LOS USUARIOS")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(usuariosService.findAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "BUSCA UN USUARIO POR SU ID")
	@GetMapping("/{idUsuario}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idUsuario") Integer idUsuario
			){
				HashMap<String, Object> result = new HashMap<>();
				Usuarios usuario = usuariosService.findById(idUsuario);
				if(usuario == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idUsuario+" de los usuarios");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", usuario);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN USUARIO")
	@PostMapping
	public ResponseEntity<?>insertUsuario(
			 @RequestBody Usuarios usuario
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Usuarios usuarios =	usuariosService.findById(usuario.getIdUsuario());
				
				if(usuarios != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+usuario.getIdUsuario()+" del usuario");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				usuario.setEstado(true);
				usuariosService.insert(usuario);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", usuarios);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}
	
	
	
	
	@ApiOperation(value="ACTUALIZAR LOS DATOS DEL USUARIO")
	@PutMapping
	public ResponseEntity<?> updateUsuario(
			  @RequestBody Usuarios ususario
			){
				HashMap<String, Object> result = new HashMap<>();
				Usuarios usuarios = usuariosService.findById(ususario.getIdUsuario());
				
				if(usuarios == null) {
					result.put("success", false);
					result.put("message", "No existe el "+ususario.getIdUsuario()+" del usuario");
				  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
				
				usuariosService.update(ususario);
				
				result.put("success", true);
				result.put("message", "Se ha actualizado correctamente");
				result.put("data", usuarios);
				
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="ELIMINAR UN REGISTRO")
	@PutMapping("/delete")
	public ResponseEntity<?> deleteUsuario(
	 @RequestBody Usuarios usuario
	){
		HashMap<String, Object> result = new HashMap<>();
		Usuarios usuarios = usuariosService.findById(usuario.getIdUsuario());
		
		if(usuarios == null) {
			result.put("success", false);
			result.put("message", "No existe el "+usuario.getUsuario()+" del usuario");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
		usuario.setEstado(false);
		usuariosService.delete(usuario);
		
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", usuarios);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<?>deleteFisicoCategoria(
	 @PathVariable(value = "idUsuario") Integer idUsuario
	){
		
		HashMap<String, Object> result = new HashMap<>();
		Usuarios usuarios = usuariosService.findById(idUsuario);
		
		if(usuarios == null) {
			result.put("success", false);
			result.put("message", "No existe el "+idUsuario+"  del usuario");
		  return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		usuariosService.deleteFisico(usuarios);
		 
		result.put("success", true);
		result.put("message", "Se ha eliminado correctamente");
		result.put("data", usuarios);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
