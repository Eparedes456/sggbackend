package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.models.Pedido;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll() {
		   return (List<Pedido>)pedidoRepository.findAll();
		}
	public Pedido findById(Integer idPedido) {
		return pedidoRepository.findById(idPedido).orElse(null);
	}
	
	public void insert(Pedido pedido) {
			
		pedidoRepository.save(pedido);
	}
	
	public void update(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public void delete(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public void deleteFisico(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
	
	
}
