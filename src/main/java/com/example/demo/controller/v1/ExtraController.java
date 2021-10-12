package com.example.demo.controller.v1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Empresa;
import com.example.demo.models.Extra;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.ExtraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/extra", produces = "Application/json")
@CrossOrigin(origins = "*")
public class ExtraController {
	
	@Autowired
	private ExtraService extraService;
	
	@ApiOperation(value = "LISTA TODOS LOS REGISTROS DE EXTRAS")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(extraService.findAll(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "BUSCA UN EXTRA POR SU ID")
	@GetMapping("/{idExtra}")
	public ResponseEntity<?>findById(
		     @PathVariable(value = "idExtra") Integer idExtra
			){
				HashMap<String, Object> result = new HashMap<>();
				Extra extra = extraService.findById(idExtra);
				if(extra == null) {
					result.put("success", false);
					result.put("message", "No existe el id "+idExtra+" del extra");
					return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
				}
				
				result.put("success", true);
				result.put("data", extra);
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
	
	@ApiOperation(value="CREAR Y GUARDAR UN EXTRA")
	@PostMapping
	public ResponseEntity<?>insertExtra(
			 @RequestBody Extra extra
			){
				HashMap<String, Object> result = new HashMap<>();
				
				Extra extras =	extraService.findById(extra.getIdExtra());
				
				if(extras != null) {
				  result.put("success", false);
				  result.put("message", "Ya existe el id "+extra.getIdExtra()+" de extra");
				  return new ResponseEntity<>(result,HttpStatus.CONFLICT);
				}
				extra.setEstado(true);
				extraService.insert(extra);
				result.put("success", true);
				result.put("message", "El resgistro se inserto correctamente");
				result.put("data", extra);
				return new ResponseEntity<>(result,HttpStatus.OK);
					
			}

}
