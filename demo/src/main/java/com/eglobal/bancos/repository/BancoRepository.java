package com.eglobal.bancos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eglobal.bancos.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {

	List<Banco> findAllByActivoTrue();
	
	boolean existsByCodigoAndNombre(String codigo, String nombre);
	
	boolean existsByCodigoAndNombreAndIdNot(String codigo, String nombre, Long id);
	
	Optional<Banco> findByIdAndActivoTrue(Long id);
}

