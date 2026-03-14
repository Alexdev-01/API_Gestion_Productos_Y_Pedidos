package com.tiendaonline.gestion.dto.pedido;

import java.util.List;

public class CrearPedidoRequest {
	
	private List<ItemPedidoRequest> items;
	
	public List<ItemPedidoRequest> getItems() {
		return items;
	}
	
	public void setItems (List<ItemPedidoRequest> items) {
		this.items = items;
	}

}
