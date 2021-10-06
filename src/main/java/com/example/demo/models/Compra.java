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
@ApiModel("Modelo de compra")
@Data
@Table(name="compra", schema = "public")
public class Compra {
	
	@Id
	@SequenceGenerator(name = "COMPRA_ID_GENERATOR", sequenceName = "compra_id_compra_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la categoria", required = false)
	
	@Column(name = "id_compra")
	private Integer	idCompra;
	private Integer id_proveedor;
	private Integer id_empleado;
	private Double	total_compra;
	private String	fecha_compra;
	private boolean estado;

}






