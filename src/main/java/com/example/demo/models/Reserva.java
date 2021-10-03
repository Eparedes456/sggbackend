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
@ApiModel("Modelo de reserva")
@Data
@Table(name="reserva", schema = "public")
public class Reserva {
	
	@Id
	@SequenceGenerator(name = "RESERVA_ID_GENERATOR", sequenceName = "reserva_id_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de reserva", required = false)
	
	
	@Column(name = "id_reserva")
	private Integer	idReserva;
	private Integer idCliente;
	private Integer idMesa;
	private String	fecha_reserva;
	private boolean estado;
	
	
	
	

}
