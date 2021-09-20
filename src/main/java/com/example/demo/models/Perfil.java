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
@ApiModel("Modelo de perfil")
@Data
@Table(name="perfil", schema = "public")


public class Perfil {
	
	@Id
	@SequenceGenerator(name = "PERFIL_ID_GENERATOR", sequenceName = "perfil_id_perfil_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFIL_ID_GENERATOR")
	@ApiModelProperty(value = "id principal de la mesa", required = false)

	@Column(name="id_perfil")
	private Integer idPerfil;
	
	private String nombre_perfil;
	private String descripcion_perfil;
	private boolean estado;
}
