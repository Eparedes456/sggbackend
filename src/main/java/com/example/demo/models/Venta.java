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
@ApiModel("Modelo de venta")
@Data
@Table(name="venta", schema = "public")
public class Venta {
	
	@Id
	@SequenceGenerator(name = "VENTA_ID_GENERATOR", sequenceName = "venta_id_venta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la venta", required = false)
	
	@Column(name = "id_venta")
	private Integer	idVenta;
	private Integer	id_pedido;
	private Integer	id_cliente;
	private Double	total_venta;
	private String	fecha_venta;
	private boolean	estado;
	

}
