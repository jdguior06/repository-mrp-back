package com.sistema.inventory.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.inventory.entity.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
	
    List<Lote> findBySku(String sku);
    
    List<Lote> findByAlmacen(String almacen);
    
    List<Lote> findBySkuAndAlmacen(String sku, String almacen);
    
    @Query("SELECT l FROM Lote l WHERE l.fechaVencimiento <= :fechaLimite ORDER BY l.fechaVencimiento ASC")
    List<Lote> findLotesProximosVencer(LocalDate fechaLimite);
    
    @Query("SELECT l FROM Lote l WHERE l.fechaVencimiento < CURRENT_DATE")
    List<Lote> findLotesVencidos();
    
    @Query("SELECT l FROM Lote l WHERE l.sku = :sku AND l.cantidad > 0 AND l.fechaVencimiento > CURRENT_DATE ORDER BY l.fechaVencimiento ASC")
    List<Lote> findLotesDisponiblesBySku(String sku);
    
    List<Lote> findByNumeroLote(String numeroLote);

}
