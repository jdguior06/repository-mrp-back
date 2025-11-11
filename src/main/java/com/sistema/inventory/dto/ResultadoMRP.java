package com.sistema.inventory.dto;
import com.sistema.inventory.entity.OrdenCompra;
import java.util.List;

public class ResultadoMRP {
    private Integer ordenesGeneradas;
    private List<OrdenCompra> ordenes;
    private String mensaje;
    
    public Integer getOrdenesGeneradas() { return ordenesGeneradas; }
    public void setOrdenesGeneradas(Integer ordenesGeneradas) { this.ordenesGeneradas = ordenesGeneradas; }
    
    public List<OrdenCompra> getOrdenes() { return ordenes; }
    public void setOrdenes(List<OrdenCompra> ordenes) { this.ordenes = ordenes; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}