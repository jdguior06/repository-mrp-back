package com.sistema.inventory.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.inventory.entity.Inventario;
import com.sistema.inventory.entity.OrdenCompra;
import com.sistema.inventory.entity.Producto;
import com.sistema.inventory.repository.InventarioRepository;
import com.sistema.inventory.repository.OrdenCompraRepository;
import com.sistema.inventory.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class MrpService {
	
    @Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    
    // ✅ Ejecutar MRP completo
    @Transactional
    public List<OrdenCompra> ejecutarMRP() {
        List<OrdenCompra> ordenesGeneradas = new ArrayList<>();
        
        // 1. Obtener todos los productos con stock bajo
        List<Inventario> inventarioBajo = inventarioRepository.findInventarioBajoStock();
        
        // 2. Para cada producto, calcular orden de compra
        for (Inventario inv : inventarioBajo) {
            Producto producto = productoRepository.findById(inv.getSku()).orElse(null);
            if (producto == null) continue;
            
            // Calcular cantidad a ordenar (simple: alcanzar el doble del punto de reorden)
            Integer cantidadFaltante = inv.getPuntoReorden() - inv.getCantidadDisponible();
            Integer cantidadOrden = Math.max(cantidadFaltante, inv.getStockSeguridad());
            
            // EOQ simplificado (puedes mejorar con fórmula real después)
            Integer eoq = calcularEOQ(producto, inv);
            Integer cantidadFinal = Math.max(cantidadOrden, eoq);
            
            // Crear orden de compra
            OrdenCompra orden = new OrdenCompra();
            orden.setNumeroOc("OC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            orden.setSku(inv.getSku());
            orden.setCantidadSugerida(cantidadFinal);
            orden.setEstado("sugerida");
            orden.setCreadoPor("motor_mrp");
            orden.setCreadoEn(LocalDateTime.now());
            
            ordenesGeneradas.add(ordenCompraRepository.save(orden));
        }
        
        return ordenesGeneradas;
    }
    
    // ✅ Calcular EOQ (Economic Order Quantity) - versión simplificada
    private Integer calcularEOQ(Producto producto, Inventario inventario) {
        // Fórmula EOQ: √(2 * D * S / H)
        // D = demanda anual (simplificado: punto de reorden * 52 semanas)
        // S = costo por orden (asumimos $50)
        // H = costo de mantener inventario (asumimos 20% del costo unitario)
        
        if (producto.getCostoUnitario() == null || producto.getCostoUnitario() == 0) {
            return inventario.getPuntoReorden() * 2; // Fallback
        }
        
        double demandaAnual = inventario.getPuntoReorden() * 52.0;
        double costoPorOrden = 50.0;
        double costoMantener = producto.getCostoUnitario() * 0.20;
        
        double eoq = Math.sqrt((2 * demandaAnual * costoPorOrden) / costoMantener);
        
        return Math.max((int) Math.round(eoq), inventario.getPuntoReorden());
    }
    
    // Obtener órdenes de compra
    public List<OrdenCompra> obtenerOrdenes(String estado) {
        if (estado == null || estado.isEmpty()) {
            return ordenCompraRepository.findAll();
        }
        return ordenCompraRepository.findByEstado(estado);
    }
    
    // Aprobar orden
    @Transactional
    public OrdenCompra aprobarOrden(Long id) {
        OrdenCompra orden = ordenCompraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        
        orden.setEstado("aprobada");
        return ordenCompraRepository.save(orden);
    }

}
