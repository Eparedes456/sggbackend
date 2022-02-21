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
@ApiModel("Modelo de Unidad de medida")
@Data
@Table(name="unidad_medida", schema = "public")
public class UnidadMedida {

	@Id
	@SequenceGenerator(name = "unidad_medida_id_unidad_medida_seq", sequenceName = "unidad_medida_id_unidad_medida_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidad_medida_id_unidad_medida_seq")
	@ApiModelProperty(value = "id principal de las unidades de medida", required = false)
	
	
	@Column(name = "id_unidad_medida")
	private Integer idUnidadMedida;
	private String	nombre;
	private String	abreviatura;
	private boolean estado;
	
}
