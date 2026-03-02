package com.tiendaonline.gestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiendaonline.gestion.model.Categoria;
import com.tiendaonline.gestion.model.Producto;

public interface CategoriaRepository extends JpaRepository<Producto, Long>{
	
	Optional<Categoria> findByNombre(String nombre);

}
