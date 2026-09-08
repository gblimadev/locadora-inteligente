package com.devs.locadora.carros.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {

    @Schema(
            description = "Data de início da reserva",
            example = "2026-09-15"
    )
    @NotNull(message = "A data início é obrigatória")
    private LocalDate dataInicio;

    @Schema(
            description = "Data de término da reserva",
            example = "2026-09-20"
    )
    @NotNull(message = "A data fim é obrigatória")
    private LocalDate dataFim;

    @Schema(
            description = "Valor total da reserva, calculado automaticamente com base no período e no preço da diária",
            example = "1250.00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private BigDecimal valorTotal;

    @Schema(
            description = "ID do usuário responsável pela reserva",
            example = "1"
    )
    @NotNull(message = "O usuário é obrigatório")
    private Long usuario_id;

    @Schema(
            description = "ID do carro que será reservado",
            example = "1"
    )
    @NotNull(message = "O carro é obrigatório")
    private Long carro_id;

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

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getCarro_id() {
        return carro_id;
    }

    public void setCarro_id(Long carro_id) {
        this.carro_id = carro_id;
    }
}