package com.sistema.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sistema.inventory.entity.Inventario;
import com.sistema.inventory.service.InventarioService;

@Controller
public class InventarioController {
	
	@Autowired
    private InventarioService inventarioService;
    
    @QueryMapping
    public List<Inventario> inventario(@Argument String almacen) {
        return inventarioService.obtenerPorAlmacen(almacen);
    }
    
    @QueryMapping
    public List<Inventario> inventarioBajoStock() {
        return inventarioService.obtenerInventarioBajoStock();
    }
    
    @MutationMapping
    public Inventario ajustarStock(@Argument String sku, 
                                    @Argument String almacen, 
                                    @Argument Integer cantidad) {
        return inventarioService.ajustarStock(sku, almacen, cantidad);
    }
    
    @MutationMapping
    public Inventario reservarStock(@Argument String sku, 
                                     @Argument String almacen, 
                                     @Argument Integer cantidad) {
        return inventarioService.reservarStock(sku, almacen, cantidad);
    }

}
