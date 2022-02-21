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
@ApiModel("Modelo de la tabla presentaciones")
@Data
@Table(name="presentacion", schema = "public")
public class Presentacion {

	@Id
	@SequenceGenerator(name = "presentacion_id_presentacion_seq", sequenceName = "presentacion_id_presentacion_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentacion_id_presentacion_seq")
	@ApiModelProperty(value = "id principal de las presentaciones", required = false)
	
	
	@Column(name = "id_presentacion")
	private Integer idPresentacion;
	private String	nombre;
	private String	descripcion;
	private boolean	estado;
	
	
}