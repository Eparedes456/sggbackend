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
@ApiModel("Modelo de permisos")
@Data
@Table(name="permisos", schema = "public")
public class Permisos {

	@Id
	@SequenceGenerator(name = "PERMISOS_ID_GENERATOR", sequenceName = "permisos_id_permisos_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISOS_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de los permisos", required = false)
	
	@Column(name = "id_permisos")
	private Integer	idPermisos;
	private Integer id_modulo;
	private Integer id_perfil;
	private Boolean lectura;
	private Boolean insertar;
	private Boolean editar;
	private Boolean eliminar;
	private Boolean reporte;
	private Boolean imprimir;
	private Boolean todos;
}
