package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@ApiModel("Modelo de empleado")
@Data
@Table(name="empleado", schema = "public")
public class Empleado {
	
	@Id
	@SequenceGenerator(name = "EMPLEADO_ID_GENERATOR", sequenceName = "empleados_id_empleado_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLEADO_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de reserva", required = false)
	
	
	@Column(name = "id_empleado")
	private Integer	idEmpleado;
	private Integer	id_perfil;
	private String	nombre_empleado;
	private String 	apellidos_empleado;
	private Double	num_dni;
	private Double	telefono;
	private String	direccion;
	private String	usuario;
	private String	contrasena;
	private String	foto;
	private boolean	estado;
	
	

}
