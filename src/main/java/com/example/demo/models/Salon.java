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
@ApiModel("Modelo de Salon")
@Data
@Table(name="salones", schema = "public")

public class Salon {
	
	@Id
	@SequenceGenerator(name = "SALONES_ID_GENERATOR", sequenceName = "salones_id_salon_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALONES_ID_GENERATOR")
	@ApiModelProperty(value = "id principal del salon", required = false)
	
	@Column(name = "id_salon")
	private Integer idSalon;
	
	private String nombre_salon;
	private boolean estado;

}
