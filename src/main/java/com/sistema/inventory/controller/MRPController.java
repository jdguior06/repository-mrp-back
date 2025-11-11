package com.sistema.inventory.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sistema.inventory.dto.ResultadoMRP;
import com.sistema.inventory.entity.OrdenCompra;
import com.sistema.inventory.service.MrpService;

import java.util.List;

@Controller
public class MRPController {
    
    @Autowired
    private MrpService mrpService;
    
    @QueryMapping
    public List<OrdenCompra> ordenesCompra(@Argument String estado) {
        return mrpService.obtenerOrdenes(estado);
    }
    
    @QueryMapping
    public ResultadoMRP ejecutarMRP() {
        List<OrdenCompra> ordenes = mrpService.ejecutarMRP();
        
        ResultadoMRP resultado = new ResultadoMRP();
        resultado.setOrdenesGeneradas(ordenes.size());
        resultado.setOrdenes(ordenes);
        resultado.setMensaje("MRP ejecutado exitosamente. " + ordenes.size() + " órdenes generadas.");
        
        return resultado;
    }
    
    @MutationMapping
    public OrdenCompra aprobarOrden(@Argument Long id) {
        return mrpService.aprobarOrden(id);
    }
}