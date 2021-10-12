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
@ApiModel("Modelo de extra")
@Data
@Table(name="extra", schema = "public")
public class Extra {
	
	@Id
	@SequenceGenerator(name = "EXTRA_ID_GENERATOR", sequenceName = "extra_id_extra_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXTRA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la tabla extra", required = false)
	
	@Column(name = "id_extra")
	private Integer idExtra;
	private String	nombre_extra;
	private	Integer	cantidad;
	private Double	precio_extra;
	private boolean	estado;
	
	
	
}
