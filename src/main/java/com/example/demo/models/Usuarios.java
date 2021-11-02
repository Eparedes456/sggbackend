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
@ApiModel("Modelo de Usuario")
@Data
@Table(name="usuario", schema = "public")

public class Usuarios {

	@Id
	@SequenceGenerator(name = "USUARIO_ID_GENERATOR", sequenceName = "usuario_id_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_GENERATOR")
	@ApiModelProperty(value = "id principal del usuario", required = false)
	
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	private Integer id_empleado;
	
	private Integer	id_perfil;
	
	private String	usuario;
	
	private String	contrasena;
	
	private boolean	estado;
	
	
	
}
