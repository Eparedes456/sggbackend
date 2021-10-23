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
@ApiModel("Modelo de producto extra")
@Data
@Table(name="producto_extra", schema = "public")
public class Producto_extra {
	
	@Id
	@SequenceGenerator(name = "PRODUCTO_EXTRA_ID_GENERATOR", sequenceName = "producto_extra_id_extra_producto_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_EXTRA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la tabla detalle producto extra", required = false)
	
	
	@Column(name = "id_extra_producto")
	private Integer	idProductoExtra;
	private Integer id_extra;
	private	Integer id_categoria;
	//private boolean estado;
	
	
}
