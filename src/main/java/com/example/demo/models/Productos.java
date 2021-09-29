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
@ApiModel("Modelo de productos")
@Data
@Table(name="producto", schema = "public")
public class Productos {
	
	@Id
	@SequenceGenerator(name = "PRODUCTOS_ID_GENERATOR", sequenceName = "producto_id_producto_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de los clientes", required = false)
	
	@Column(name = "id_producto")
	private Integer idProducto;
	private String 	nombre_producto;
	private Double 	precio;
	private Integer	cantidad;
	private String 	imagen;
	private String 	abreviatura;
	private boolean compuesto;
	private boolean estado;

	
}
