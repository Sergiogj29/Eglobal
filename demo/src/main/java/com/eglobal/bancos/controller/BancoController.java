package com.eglobal.bancos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eglobal.bancos.dto.BancoRequestDTO;
import com.eglobal.bancos.dto.BancoResponseDTO;
import com.eglobal.bancos.service.BancoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/bancos")
@RequiredArgsConstructor
public class BancoController {
	private final BancoService bancoService;
	
	@PostMapping
	public ResponseEntity<BancoResponseDTO> crear(@Valid @RequestBody BancoRequestDTO request) {
		log.info("Creando banco: {}", request.getCodigo());
		BancoResponseDTO response = bancoService.crear(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
    @GetMapping
    public ResponseEntity<List<BancoResponseDTO>> listar() {
        log.info("Listando bancos activos");
        return ResponseEntity.ok(bancoService.listarActivos());
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<BancoResponseDTO> obtenerPorId(@PathVariable Long id) {
		log.info("Obteniendo banco", id);
		return ResponseEntity.ok(bancoService.obtenerPorId(id));
	}
	
    @PutMapping("/{id}")
	public ResponseEntity<BancoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody BancoRequestDTO request) {
		log.info("Actualizando banco", id);
		return ResponseEntity.ok(bancoService.actualizar(id, request));
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Eliminando banco", id);
        bancoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
