package com.devs.locadora.carros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CarroDTO {

    @NotBlank(message = "A marca do carro é obrigatória")
    private String marca;

    @NotBlank(message = "O modelo do carro é obrigatório")
    private String modelo;

    @NotNull(message = "O ano do carro é obrigatório")
    private Integer ano;

    @NotNull(message = "O tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "O tipo do combustível é obrigatório")
    private String combustivel;

    @NotBlank(message = "O tipo do cambio é obrigatório")
    private String cambio;

    @NotBlank(message = "O nível desempenho é obrigatório")
    private String nivelDesempenho;

    @NotBlank(message = "O nível desempenho é obrigatório")
    private String nivelEconomia;

    @NotBlank(message = "O nível conforto é obrigatório")
    private String nivelConforto;

    @NotNull(message = "A quantidade de lugares é obrigatório")
    private Integer lugares;

    @NotNull(message = "O portaMalas é necessário")
    private Integer portaMalas;

    private BigDecimal precoDiaria;

    private Boolean disponivel;

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
