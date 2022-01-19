package com.example.demo.config.security.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo de autentificación")
public class JwtAuthenticationRequest {
	
	@ApiModelProperty(value = "username ", required = true)
	private String username;
	@ApiModelProperty(value = "Password", required = true)
	private String password;
	
	public JwtAuthenticationRequest() {
		super();
	}
	
	public JwtAuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	


}
