package com.sistema.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.inventory.entity.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
	
    Optional<Inventario> findBySkuAndAlmacen(String sku, String almacen);
    
    List<Inventario> findBySku(String sku);
    
    List<Inventario> findByAlmacen(String almacen);
    
    @Query("SELECT i FROM Inventario i WHERE i.cantidadDisponible <= i.puntoReorden")
    List<Inventario> findInventarioBajoStock();
    
    @Query("SELECT i FROM Inventario i WHERE i.cantidadDisponible <= i.stockSeguridad")
    List<Inventario> findInventarioCritico();
    
    @Query("SELECT SUM(i.cantidadDisponible) FROM Inventario i WHERE i.sku = :sku")
    Integer getTotalDisponibleBySku(String sku);

}
