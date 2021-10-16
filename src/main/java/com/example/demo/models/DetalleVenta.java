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
@ApiModel("Modelo de detalle venta")
@Data
@Table(name="detalle_venta", schema = "public")
public class DetalleVenta{
	
	@Id
	@SequenceGenerator(name = "DETALLE_VENTA_ID_GENERATOR", sequenceName = "detalle_venta_id_detalle_venta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLE_VENTA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la venta", required = false)
	
	@Column(name = "id_detalle_venta")
	private Integer	idDetalleVenta;
	
	private Integer id_venta;
	
	private Integer id_producto;
	
	private Integer	cantidad;
	
	private Double	precio;
	
	private boolean	estado;
	
	
}