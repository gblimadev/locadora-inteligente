package com.devs.locadora.carros.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String marca;

	private String modelo;

	private Integer ano;

	private String tipo;

	private String combustivel;

	private String cambio;

	private String nivelDesempenho;

	private String nivelEconomia;

	private String nivelConforto;

	private Integer lugares;

	private Integer portaMalas;

	private BigDecimal precoDiaria;

	private Boolean disponivel;

	public Carro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getNivelDesempenho() {
		return nivelDesempenho;
	}

	public void setNivelDesempenho(String nivelDesempenho) {
		this.nivelDesempenho = nivelDesempenho;
	}

	public String getNivelEconomia() {
		return nivelEconomia;
	}

	public void setNivelEconomia(String nivelEconomia) {
		this.nivelEconomia = nivelEconomia;
	}

	public String getNivelConforto() {
		return nivelConforto;
	}

	public void setNivelConforto(String nivelConforto) {
		this.nivelConforto = nivelConforto;
	}

	public Integer getLugares() {
		return lugares;
	}

	public void setLugares(Integer lugares) {
		this.lugares = lugares;
	}

	public Integer getPortaMalas() {
		return portaMalas;
	}

	public void setPortaMalas(Integer portaMalas) {
		this.portaMalas = portaMalas;
	}

	public BigDecimal getPrecoDiaria() {
		return precoDiaria;
	}

	public void setPrecoDiaria(BigDecimal precoDiaria) {
		this.precoDiaria = precoDiaria;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	
	
}
