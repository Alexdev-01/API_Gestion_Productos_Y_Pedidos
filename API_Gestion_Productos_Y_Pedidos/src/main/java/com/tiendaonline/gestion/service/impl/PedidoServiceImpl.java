package com.tiendaonline.gestion.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.ItemPedidoRequest;
import com.tiendaonline.gestion.model.DetallePedido;
import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.model.Usuario;
import com.tiendaonline.gestion.repository.PedidoRepository;
import com.tiendaonline.gestion.repository.ProductoRepository;
import com.tiendaonline.gestion.repository.UsuarioRepository;
import com.tiendaonline.gestion.service.PedidoService;

import jakarta.transaction.Transactional;

//Implementación de la interfaz PedidoService, que maneja la lógica de negocio relacionada con los pedidos
@Service	// Marca esta clase como un componente de servicio en Spring
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final ProductoRepository productoRepository;
	private final UsuarioRepository usuarioRepository;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.productoRepository = productoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	
	@Override
	public Pedido crearPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> obtenerPedidosUsuario(Usuario usuario) {
		return pedidoRepository.findByUsuario(usuario);
	}

	@Override
	public List<Pedido> obtenerTodos() {
		return pedidoRepository.findAll();
	}


	@Override
	@Transactional	// Asegura que todas las operaciones dentro de este método se ejecuten en una sola transacción
	// Método para crear un nuevo pedido a partir de una solicitud y el nombre de usuario del cliente
	public Pedido crearPedido(CrearPedidoRequest request, String username) {
		
		Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow();
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		
		BigDecimal total = BigDecimal.ZERO;
		
		for (ItemPedidoRequest item : request.getItems()) {
			Producto producto = productoRepository.findById(item.getProductoId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
			
			if (producto.getStock() < item.getCantidad()) {
				throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
			}
			
			//Reducir Stock del producto
			producto.setStock(producto.getStock() - Item.getCantidad());
			
			DetallePedido detalle = new DetallePedido();
			
			detalle.setProducto(producto);
			detalle.setCantidad(item.getCantidad());
			detalle.setPrecio(producto.getPrecio());
			
			detalle.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
			
			pedido.addDetalle(detalle);
			
			total = total.add(detalle.getSubtotal());
			
			}
		
		pedido.setTotal(total);
		
		return pedidoRepository.save(pedido);
	}
	
	
	

}
