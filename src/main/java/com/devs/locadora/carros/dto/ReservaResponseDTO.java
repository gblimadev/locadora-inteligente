package com.devs.locadora.carros.dto;

import com.devs.locadora.carros.entities.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaResponseDTO {

    @Schema(
            description = "Identificador único da reserva",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Data de início da reserva",
            example = "2026-09-15"
    )
    private LocalDate dataInicio;

    @Schema(
            description = "Data de término da reserva",
            example = "2026-09-20"
    )
    private LocalDate dataFim;

    @Schema(
            description = "Valor total da reserva, calculado automaticamente com base no período e no preço da diária",
            example = "1250.00"
    )
    private BigDecimal valorTotal;

    @Schema(
            description = "Status atual da reserva",
            example = "CONFIRMADA"
    )
    private StatusReserva status;

    @Schema(
            description = "ID do usuário responsável pela reserva",
            example = "1"
    )
    private Long usuario_id;

    @Schema(
            description = "ID do carro reservado",
            example = "1"
    )
    private Long carro_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long id) {
        this.usuario_id = id;
    }

    public Long getCarro_id() {
        return carro_id;
    }

    public void setCarro_id(Long id) {
        this.carro_id = id;
    }
}