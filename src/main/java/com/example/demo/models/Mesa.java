package com.example.demo.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Entity
@ApiModel("Modelo de mesa")
@Data
@Table(name="mesa", schema = "public")

public class Mesa {
	
	@Id
	@SequenceGenerator(name = "MESA_ID_GENERATOR", sequenceName = "mesa_id_mesa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESA_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la mesa", required = false)
	
	@Column(name = "id_mesa")
	private Integer idMesa;
	
	
	private Integer num_mesa;
	
	private String descripcion_mesa;
	
	private Integer idSalon;
	//@ApiModelProperty(value="Modelo salon", required = true)
	//@ManyToOne
	//@JoinColumn(name="id_salon")
	//private Integer id_Salon;
	//private Salon salon;
	
	private boolean estado;
	
	
	

}
