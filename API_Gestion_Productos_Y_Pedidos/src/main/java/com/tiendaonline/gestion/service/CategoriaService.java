package com.tiendaonline.gestion.service;

import java.util.List;

import com.tiendaonline.gestion.model.Categoria;

public interface CategoriaService {
	
	Categoria crearCategoria(Categoria categoria);
	
    List<Categoria> listarTodas();

}
