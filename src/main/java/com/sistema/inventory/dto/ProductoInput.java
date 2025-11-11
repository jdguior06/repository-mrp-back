package com.sistema.inventory.dto;

public class ProductoInput {
	
	private String nombre;
    private String categoria;
    private Double costoUnitario;
    private Double precioVenta;
    private Integer diasLeadTime;
    
    public ProductoInput() {}
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Double getCostoUnitario() {
        return costoUnitario;
    }
    
    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    public Double getPrecioVenta() {
        return precioVenta;
    }
    
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    public Integer getDiasLeadTime() {
        return diasLeadTime;
    }
    
    public void setDiasLeadTime(Integer diasLeadTime) {
        this.diasLeadTime = diasLeadTime;
    }

}
