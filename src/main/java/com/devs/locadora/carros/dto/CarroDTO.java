package com.devs.locadora.carros.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarroDTO {

    @Schema(description = "Marca do carro", example = "Toyota")
    @NotBlank(message = "A marca do carro é obrigatória")
    private String marca;

    @Schema(description = "Modelo do carro", example = "Corolla")
    @NotBlank(message = "O modelo do carro é obrigatório")
    private String modelo;

    @Schema(description = "Ano de fabricação do carro", example = "2026")
    @NotNull(message = "O ano do carro é obrigatório")
    private Integer ano;

    @Schema(description = "Tipo do carro", example = "Sedan")
    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @Schema(description = "Tipo de combustível utilizado pelo carro", example = "Flex")
    @NotBlank(message = "O tipo de combustível é obrigatório")
    private String combustivel;

    @Schema(description = "Tipo de câmbio do carro", example = "Automático")
    @NotBlank(message = "O tipo de câmbio é obrigatório")
    private String cambio;

    @Schema(description = "Nível de desempenho do carro", example = "Alto")
    @NotBlank(message = "O nível de desempenho é obrigatório")
    private String nivelDesempenho;

    @Schema(description = "Nível de economia do carro", example = "Médio")
    @NotBlank(message = "O nível de economia é obrigatório")
    private String nivelEconomia;

    @Schema(description = "Nível de conforto do carro", example = "Alto")
    @NotBlank(message = "O nível de conforto é obrigatório")
    private String nivelConforto;

    @Schema(description = "Quantidade de lugares disponíveis no carro", example = "5")
    @NotNull(message = "A quantidade de lugares é obrigatória")
    private Integer lugares;

    @Schema(description = "Capacidade do porta-malas em litros", example = "470")
    @NotNull(message = "O porta-malas é necessário")
    private Integer portaMalas;

    @Schema(description = "Preço da diária do carro", example = "250.00")
    private BigDecimal precoDiaria;

    @Schema(description = "Indica se o carro está disponível para locação", example = "true")
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