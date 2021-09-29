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
@ApiModel("Modelo de clientes")
@Data
@Table(name="clientes", schema = "public")
public class Clientes {

	@Id
	@SequenceGenerator(name = "CLIENTES_ID_GENERATOR", sequenceName = "clientes_id_cliente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de los clientes", required = false)
	
	
	@Column(name = "id_cliente")
	private Integer idCliente;
	private String	nombre_cliente;
	private String	apellidos_cliente;
	private Integer num_dni;
	private String	telefono;
	private String	direccion;
	private boolean	estado;
	
	
}
