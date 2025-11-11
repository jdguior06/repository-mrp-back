package com.sistema.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.inventory.entity.Producto;
import com.sistema.inventory.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {
	    
	    @Autowired
	    private ProductoRepository productoRepository;
	    
	    public List<Producto> obtenerTodos() {
	        return productoRepository.findAll();
	    }
	    
	    public Producto obtenerPorSku(String sku) {
	        return productoRepository.findById(sku)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + sku));
	    }
	    
	    @Transactional
	    public Producto crearProducto(String sku, String nombre, String categoria, 
	                                  Double costoUnitario, Double precioVenta, Integer diasLeadTime) {
	        
	        if (productoRepository.existsById(sku)) {
	            throw new RuntimeException("El producto con SKU " + sku + " ya existe");
	        }
	        
	        Producto producto = new Producto();
	        producto.setSku(sku);
	        producto.setNombre(nombre);
	        producto.setCategoria(categoria);
	        producto.setCostoUnitario(costoUnitario);
	        producto.setPrecioVenta(precioVenta);
	        producto.setDiasLeadTime(diasLeadTime != null ? diasLeadTime : 7);
	        
	        return productoRepository.save(producto);
	    }
	    
	    @Transactional
	    public Producto actualizarProducto(String sku, String nombre, String categoria,
	                                      Double costoUnitario, Double precioVenta, Integer diasLeadTime) {
	        
	        Producto producto = productoRepository.findById(sku)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + sku));
	        
	        if (nombre != null) producto.setNombre(nombre);
	        if (categoria != null) producto.setCategoria(categoria);
	        if (costoUnitario != null) producto.setCostoUnitario(costoUnitario);
	        if (precioVenta != null) producto.setPrecioVenta(precioVenta);
	        if (diasLeadTime != null) producto.setDiasLeadTime(diasLeadTime);
	        
	        return productoRepository.save(producto);
	    }
	    
	    @Transactional
	    public Boolean eliminarProducto(String sku) {
	        if (!productoRepository.existsById(sku)) {
	            throw new RuntimeException("Producto no encontrado: " + sku);
	        }
	        
	        productoRepository.deleteById(sku);
	        return true;
	    }

}
