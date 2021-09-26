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
@ApiModel("Modelo de empresa")
@Data
@Table(name="empresa", schema = "public")
public class Empresa {
	
	@Id
	@SequenceGenerator(name = "EMPRESA_ID_GENERATOR", sequenceName = "empresa_id_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la empresa", required = false)
	
	@Column(name = "id_empresa")
	private Integer idEmpresa;
	
	private String nombre_empresa;
	private Double num_ruc;
	private String telefono;
	private String direccion;
	private Integer cod_ubigeo;
	private String logo_empresa;
	private boolean estado;

}
