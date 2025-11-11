package com.sistema.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_compra")
@Data
public class OrdenCompra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_oc", unique = true, length = 50)
    private String numeroOc;
    
    @Column(length = 50)
    private String sku;
    
    @Column(name = "cantidad_sugerida")
    private Integer cantidadSugerida;
    
    @Column(length = 20)
    private String estado = "sugerida";
    
    @Column(name = "creado_por", length = 50)
    private String creadoPor = "motor_mrp";
    
    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

}
