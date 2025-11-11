package com.sistema.inventory.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sistema.inventory.dto.LoteInput;
import com.sistema.inventory.entity.Lote;
import com.sistema.inventory.service.LoteService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LoteController {
    
    @Autowired
    private LoteService loteService;
    
    @QueryMapping
    public List<Lote> lotes(@Argument String almacen) {
        return loteService.obtenerPorAlmacen(almacen);
    }
    
    @QueryMapping
    public List<Lote> lotesProximosVencer(@Argument Integer dias) {
        return loteService.obtenerLotesProximosVencer(dias);
    }
    
    @MutationMapping
    public Lote registrarLote(@Argument LoteInput input) {
        LocalDate fechaVencimiento = LocalDate.parse(input.getFechaVencimiento());
        return loteService.registrarLote(
            input.getSku(),
            input.getNumeroLote(),
            input.getCantidad(),
            fechaVencimiento,
            input.getAlmacen()
        );
    }
}