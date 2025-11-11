package com.sistema.inventory.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventario")
@Data
public class Inventario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String sku;
    
    @Column(length = 50)
    private String almacen;
    
    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible = 0;
    
    @Column(name = "cantidad_reservada")
    private Integer cantidadReservada = 0;
    
    @Column(name = "punto_reorden")
    private Integer puntoReorden = 10;
    
    @Column(name = "stock_seguridad")
    private Integer stockSeguridad = 5;
    
    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn = LocalDateTime.now();
    
    public Integer getCantidadTotal() {
        return cantidadDisponible + cantidadReservada;
    }

}
