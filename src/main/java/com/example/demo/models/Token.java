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

import com.example.demo.config.audit.AuditModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(of = { "idToken" })
@Table(name="token", schema = "public")

public class Token extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_token")
	@SequenceGenerator(name = "PERFIL_ID_GENERATOR", sequenceName = "token_id_token_seq", allocationSize = 1)
	@GeneratedValue(generator = "PERFIL_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Integer idToken;
	private String hash;
	private String hostname;
	@Column(name = "nombre_equipo")
	private String nombreEquipo;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuarios usuario;
	
	
}
