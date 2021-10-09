package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Entity
@ApiModel("Modelo de detalle venta")
@Data
@Table(name="venta", schema = "public")
public class DetalleVenta {

}
