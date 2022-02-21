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
@ApiModel("Modelo de cajas")
@Data
@Table(name="caja", schema = "public")
public class Caja {

	@Id
	@SequenceGenerator(name = "caja_id_caja_seq", sequenceName = "caja_id_caja_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caja_id_caja_seq")
	@ApiModelProperty(value = "id principal de las cajas", required = false)
	
	
	@Column(name = "id_caja")
	private Integer idCaja;
	private String	codigo_caja;
	private String	nombre;
	private String descripcion;
	private boolean	estado;
	
	
}
