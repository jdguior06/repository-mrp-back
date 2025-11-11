package com.sistema.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {
	
	@Id
    @Column(length = 50)
    private String sku;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(length = 100)
    private String categoria; 
    
    @Column(name = "costo_unitario")
    private Double costoUnitario;
    
    @Column(name = "precio_venta")
    private Double precioVenta;
    
    @Column(name = "dias_lead_time")
    private Integer diasLeadTime = 7;

}
