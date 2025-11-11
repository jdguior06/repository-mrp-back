package com.sistema.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.inventory.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
	
    List<Producto> findByCategoria(String categoria);
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
   
    @Query("SELECT p FROM Producto p WHERE p.precioVenta < :precioMaximo")
    List<Producto> findProductosBaratos(Double precioMaximo);

}
