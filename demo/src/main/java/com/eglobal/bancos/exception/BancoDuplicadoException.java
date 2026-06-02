package com.eglobal.bancos.exception;

public class BancoDuplicadoException extends RuntimeException {
	public BancoDuplicadoException(String codigo, String nombre) {
        super("Ya existe un banco con el código '" + codigo + "' y nombre '" + nombre + "'");
	}

}
