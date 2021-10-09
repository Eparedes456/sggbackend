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
@ApiModel("Modelo de detalle compra")
@Data
@Table(name="detalle_compra", schema = "public")
public class DetalleCompra {
	
	@Id
	@SequenceGenerator(name = "DETALLE_COMPRA_ID_GENERATOR", sequenceName = "detalle_compra_id_detalle_compra_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLE_COMPRA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la categoria", required = false)
	
	@Column(name = "id_detalle_compra")
	private Integer	idDetalleCompra;
	
	private Integer id_compra;
	
	private Integer id_producto;
	
	private Integer cantidad;
	
	private Double	precio;
	
	private boolean	estado;

}
