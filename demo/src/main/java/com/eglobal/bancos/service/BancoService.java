package com.eglobal.bancos.service;

import java.util.List;

import com.eglobal.bancos.dto.BancoRequestDTO;
import com.eglobal.bancos.dto.BancoResponseDTO;

public interface BancoService {
	
	BancoResponseDTO crear(BancoRequestDTO request);
	
	List<BancoResponseDTO> listarActivos();
	
	BancoResponseDTO obtenerPorId(Long id);
	
	BancoResponseDTO actualizar(Long id, BancoRequestDTO request);
	
	void eliminar(Long id);
}
