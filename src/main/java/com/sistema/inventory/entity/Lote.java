package com.sistema.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "lotes")
@Data
public class Lote {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String sku;
    
    @Column(name = "numero_lote", length = 50)
    private String numeroLote;
    
    private Integer cantidad;
    
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;
    
    @Column(length = 50)
    private String almacen;
    
    public Long getDiasParaVencer() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
    }
    
    public Boolean estaVencido() {
        return LocalDate.now().isAfter(fechaVencimiento);
    }

}
