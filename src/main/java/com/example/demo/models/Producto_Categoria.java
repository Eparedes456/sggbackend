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
@ApiModel("Modelo de prodcuto categoria")
@Data
@Table(name="producto_categoria", schema = "public")
public class Producto_Categoria {
	
	@Id
	@SequenceGenerator(name = "PRODUCTOCATEGORIA_ID_GENERATOR", sequenceName = "producto_categoria_id_producto_categoria_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOCATEGORIA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la tabla de talle producto categoria", required = false)
	
	
	@Column(name = "id_producto_categoria")
	private Integer	idProductoCategoria;
	private Integer id_producto;
	private	Integer id_categoria;
	private boolean estado;
}
