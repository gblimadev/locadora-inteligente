package com.devs.locadora.carros.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class CarroResponseDTO {

    @Schema(
            description = "Identificador único do carro",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Marca do carro",
            example = "Toyota"
    )
    private String marca;

    @Schema(
            description = "Modelo do carro",
            example = "Corolla"
    )
    private String modelo;

    @Schema(
            description = "Ano de fabricação do carro",
            example = "2026"
    )
    private Integer ano;

    @Schema(
            description = "Tipo do carro",
            example = "Sedan"
    )
    private String tipo;

    @Schema(
            description = "Tipo de combustível utilizado pelo carro",
            example = "Flex"
    )
    private String combustivel;

    @Schema(
            description = "Tipo de câmbio do carro",
            example = "Automático"
    )
    private String cambio;

    @Schema(
            description = "Nível de desempenho do carro",
            example = "Alto"
    )
    private String nivelDesempenho;

    @Schema(
            description = "Nível de economia do carro",
            example = "Médio"
    )
    private String nivelEconomia;

    @Schema(
            description = "Nível de conforto do carro",
            example = "Alto"
    )
    private String nivelConforto;

    @Schema(
            description = "Quantidade de lugares disponíveis no carro",
            example = "5"
    )
    private Integer lugares;

    @Schema(
            description = "Capacidade do porta-malas em litros",
            example = "470"
    )
    private Integer portaMalas;

    @Schema(
            description = "Preço da diária do carro",
            example = "250.00"
    )
    private BigDecimal precoDiaria;

    @Schema(
            description = "Indica se o carro está disponível para locação",
            example = "true"
    )
    private Boolean disponivel;

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