package com.example.demo.models;

import java.util.Date;

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
@ApiModel("Modelo de pedido")
@Data
@Table(name="pedidos", schema = "public")
public class Pedido {
	@Id
	@SequenceGenerator(name = "PEDIDOS_ID_GENERATOR", sequenceName = "pedidos_id_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de pedidos", required = false)
	
	
	@Column(name = "id_pedido")
	private Integer	idPedido;
	private Integer id_cliente;
	private Integer id_mesa;
	private Integer id_empleado;
	private Double	total_pedido;
	private Date	fecha_pedido;
	private boolean	estado;

}
