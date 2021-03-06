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
@ApiModel("Modelo de categoria")
@Data
@Table(name="categoria", schema = "public")
public class Categoria {
	
	@Id
	@SequenceGenerator(name = "categoria_id_categoria_seq", sequenceName = "categoria_id_categoria_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_categoria_seq")
	@ApiModelProperty(value = "id principal de la categoria", required = false)
	
	
	@Column(name = "id_categoria")
	private Integer	idCategoria;
	private String	nombre_categoria;
	private String	descripcion_categoria;
	private boolean	estado;

}
