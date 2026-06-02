package com.eglobal.bancos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bancos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nombre;
	
	@Column(nullable = false, unique = true, length = 20)
	private String codigo;
	
	@Column(nullable = false)
	@Builder.Default
	private Boolean activo = true;
}
