package com.eglobal.bancos.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {
	
	private int status;
	private String error;
	private String mensaje;
	private List<String> detalles;
	private LocalDateTime timestamp;

}
