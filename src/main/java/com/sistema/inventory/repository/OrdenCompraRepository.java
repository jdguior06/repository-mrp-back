package com.sistema.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.inventory.entity.OrdenCompra;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
	
    List<OrdenCompra> findByEstado(String estado);
    
    List<OrdenCompra> findBySku(String sku);
    
    List<OrdenCompra> findByEstadoOrderByCreadoEnDesc(String estado);
    
    OrdenCompra findByNumeroOc(String numeroOc);
    
    @Query("SELECT COUNT(o) FROM OrdenCompra o WHERE o.estado = :estado")
    Long countByEstado(String estado);

}
