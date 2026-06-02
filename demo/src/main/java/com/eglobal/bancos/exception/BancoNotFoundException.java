package com.eglobal.bancos.exception;

public class BancoNotFoundException extends RuntimeException{
	public BancoNotFoundException(Long id) {
		super("Banco no encontrado con id: " + id);
	}

}
