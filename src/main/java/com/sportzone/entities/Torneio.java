package com.sportzone.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "torneio")
public class Torneio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 100)
	private Long id;
	
	private String nome;
	
	private String data;
	
	private double taxa;
	
	private String imagem;

	private String genero;
	
	private String faixaEtaria;
	
	private String cidade;
	
	private String rua;
	
	private String bairro;
	
	private String numero;
	
	private String classe;
	
}