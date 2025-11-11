package com.sistema.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sistema.inventory.dto.ProductoInput;
import com.sistema.inventory.entity.Producto;
import com.sistema.inventory.service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
    private ProductoService productoService;
    
    @QueryMapping
    public List<Producto> productos() {
        return productoService.obtenerTodos();
    }
    
    @QueryMapping
    public Producto productoPorSku(@Argument String sku) {
        return productoService.obtenerPorSku(sku);
    }
    
    @MutationMapping
    public Producto crearProducto(@Argument String sku, @Argument ProductoInput input) {
        return productoService.crearProducto(
            sku,
            input.getNombre(),
            input.getCategoria(),
            input.getCostoUnitario(),
            input.getPrecioVenta(),
            input.getDiasLeadTime()
        );
    }
    
    @MutationMapping
    public Producto actualizarProducto(@Argument String sku, @Argument ProductoInput input) {
        return productoService.actualizarProducto(
            sku,
            input.getNombre(),
            input.getCategoria(),
            input.getCostoUnitario(),
            input.getPrecioVenta(),
            input.getDiasLeadTime()
        );
    }
    
    @MutationMapping
    public Boolean eliminarProducto(@Argument String sku) {
        return productoService.eliminarProducto(sku);
    }

}
