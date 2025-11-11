package com.sistema.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.inventory.entity.Inventario;
import com.sistema.inventory.repository.InventarioRepository;
import com.sistema.inventory.repository.LoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventarioService {
	
	@Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private LoteRepository loteRepository;
    
    public List<Inventario> obtenerTodo() {
        return inventarioRepository.findAll();
    }
    
    public List<Inventario> obtenerPorAlmacen(String almacen) {
        if (almacen == null || almacen.isEmpty()) {
            return inventarioRepository.findAll();
        }
        return inventarioRepository.findByAlmacen(almacen);
    }
    
    public List<Inventario> obtenerInventarioBajoStock() {
        return inventarioRepository.findInventarioBajoStock();
    }
    
    @Transactional
    public Inventario ajustarStock(String sku, String almacen, Integer cantidad) {
        Inventario inventario = inventarioRepository.findBySkuAndAlmacen(sku, almacen)
            .orElseGet(() -> {
                Inventario nuevo = new Inventario();
                nuevo.setSku(sku);
                nuevo.setAlmacen(almacen);
                nuevo.setCantidadDisponible(0);
                nuevo.setCantidadReservada(0);
                return nuevo;
            });
        
        Integer nuevaCantidad = inventario.getCantidadDisponible() + cantidad;
        if (nuevaCantidad < 0) {
            throw new RuntimeException("Stock no puede ser negativo");
        }
        
        inventario.setCantidadDisponible(nuevaCantidad);
        inventario.setActualizadoEn(LocalDateTime.now());
        
        return inventarioRepository.save(inventario);
    }
    
    @Transactional
    public Inventario reservarStock(String sku, String almacen, Integer cantidad) {
        Inventario inventario = inventarioRepository.findBySkuAndAlmacen(sku, almacen)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        
        if (inventario.getCantidadDisponible() < cantidad) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + inventario.getCantidadDisponible());
        }
        
        inventario.setCantidadDisponible(inventario.getCantidadDisponible() - cantidad);
        inventario.setCantidadReservada(inventario.getCantidadReservada() + cantidad);
        inventario.setActualizadoEn(LocalDateTime.now());
        
        return inventarioRepository.save(inventario);
    }
    
    @Transactional
    public Inventario confirmarVenta(String sku, String almacen, Integer cantidad) {
        Inventario inventario = inventarioRepository.findBySkuAndAlmacen(sku, almacen)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        
        if (inventario.getCantidadReservada() < cantidad) {
            throw new RuntimeException("Cantidad reservada insuficiente");
        }
        
        inventario.setCantidadReservada(inventario.getCantidadReservada() - cantidad);
        inventario.setActualizadoEn(LocalDateTime.now());
        
        return inventarioRepository.save(inventario);
    }

}
