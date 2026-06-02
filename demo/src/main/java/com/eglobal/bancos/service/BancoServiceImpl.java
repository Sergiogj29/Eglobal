package com.eglobal.bancos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eglobal.bancos.dto.BancoRequestDTO;
import com.eglobal.bancos.dto.BancoResponseDTO;
import com.eglobal.bancos.exception.BancoDuplicadoException;
import com.eglobal.bancos.exception.BancoNotFoundException;
import com.eglobal.bancos.model.Banco;
import com.eglobal.bancos.repository.BancoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BancoServiceImpl implements BancoService {
	
	private final BancoRepository bancoRepository;
	
	private BancoResponseDTO toDTO(Banco banco) {
		return BancoResponseDTO.builder()
				.id(banco.getId())
				.nombre(banco.getNombre())
				.codigo(banco.getCodigo())
				.activo(banco.getActivo())
				.build();
	}

	@Override
	@Transactional
	public BancoResponseDTO crear(BancoRequestDTO request) {
        log.info("Creando banco con código: {} y nombre: {}", request.getCodigo(), request.getNombre());
		if (bancoRepository.existsByCodigoAndNombre(request.getCodigo(), request.getNombre())) {
			throw new BancoDuplicadoException(request.getCodigo(), request.getNombre());
		}
		Banco banco = Banco.builder()
				.nombre(request.getNombre())
				.codigo(request.getCodigo())
				.activo(true)
				.build();
		
		Banco guardado = bancoRepository.save(banco);
        log.info("Banco creado exitosamente con id: {}", guardado.getId());
		return toDTO(guardado);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<BancoResponseDTO> listarActivos() {
        log.info("Listando bancos activos");
		return bancoRepository.findAllByActivoTrue()
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public BancoResponseDTO obtenerPorId(Long id) {
        log.info("Buscando banco con id: {}", id);
		Banco banco = bancoRepository.findByIdAndActivoTrue(id)
				.orElseThrow(() -> new BancoNotFoundException(id));
		return toDTO(banco);
	}

	@Override
	@Transactional
	public BancoResponseDTO actualizar(Long id, BancoRequestDTO request) {
        log.info("Actualizando banco con id: {}", id);
		Banco banco = bancoRepository.findByIdAndActivoTrue(id)
				.orElseThrow(() -> new BancoNotFoundException(id));
		if (bancoRepository.existsByCodigoAndNombreAndIdNot(request.getCodigo(), request.getNombre(),id)) {
			throw new BancoDuplicadoException(request.getCodigo(), request.getNombre());
		}
		banco.setNombre(request.getNombre());
		banco.setCodigo(request.getCodigo());
		
		Banco actualizado = bancoRepository.save(banco);
		log.info("Banco actualizado exitosamente con id: {}", actualizado.getId());
		return toDTO(actualizado);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {		
		log.info("Eliminado banco con id: {}", id);
		Banco banco = bancoRepository.findByIdAndActivoTrue(id)
				.orElseThrow(() -> new BancoNotFoundException(id));
		banco.setActivo(false);
		bancoRepository.save(banco);
		log.info("Banco con id: {} marcado como inactivo", id);
	}
	

	
	
}
