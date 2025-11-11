package com.sistema.inventory.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.inventory.entity.Lote;
import com.sistema.inventory.repository.LoteRepository;
import jakarta.transaction.Transactional;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;

	public List<Lote> obtenerTodos() {
		return loteRepository.findAll();
	}

	public List<Lote> obtenerPorAlmacen(String almacen) {
		if (almacen == null || almacen.isEmpty()) {
			return loteRepository.findAll();
		}
		return loteRepository.findByAlmacen(almacen);
	}

	public List<Lote> obtenerLotesProximosVencer(Integer dias) {
		LocalDate fechaLimite = LocalDate.now().plusDays(dias);
		return loteRepository.findLotesProximosVencer(fechaLimite);
	}

	public List<Lote> obtenerLotesVencidos() {
		return loteRepository.findLotesVencidos();
	}

	@Transactional
	public Lote registrarLote(String sku, String numeroLote, Integer cantidad, LocalDate fechaVencimiento,
			String almacen) {
		Lote lote = new Lote();
		lote.setSku(sku);
		lote.setNumeroLote(numeroLote);
		lote.setCantidad(cantidad);
		lote.setFechaVencimiento(fechaVencimiento);
		lote.setAlmacen(almacen);

		return loteRepository.save(lote);
	}

}
