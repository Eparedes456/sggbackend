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
@ApiModel("Modelo de proveedor")
@Data
@Table(name="proveedor", schema = "public")
public class Proveedor {
	
	@Id
	@SequenceGenerator(name = "PROVEEDOR_ID_GENERATOR", sequenceName = "proveedor_id_proveedor_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDOR_ID_GENERATOR")
	@ApiModelProperty(value = "id principal del proveedor", required = false)
	
	@Column(name = "id_proveedor")
	private Integer idProveedor;
	
	private String	nombre_proveedor;
	
	private String	razon_social;
	
	private Integer	num_ruc;
	
	private String	nombre_representante;
	
	private String 	telefono;
	
	private String 	direccion_proveedor;
	
	private Integer cod_ubigeo;
	
	private boolean	estado;

}
