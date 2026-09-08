package com.devs.locadora.carros.dto;

import java.time.LocalDate;

import com.devs.locadora.carros.entities.enums.TipoManutencao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ManutencaoDTO {

    @Schema(
            description = "Descrição da manutenção realizada no carro",
            example = "Troca de óleo e filtros"
    )
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @Schema(
            description = "Data de início da manutenção",
            example = "2026-09-10"
    )
    @NotNull(message = "A data início é obrigatória")
    private LocalDate dataInicio;

    @Schema(
            description = "Data de término da manutenção",
            example = "2026-09-12"
    )
    @NotNull(message = "A data fim é obrigatória")
    private LocalDate dataFim;

    @Schema(
            description = "Tipo da manutenção",
            example = "PREVENTIVA"
    )
    private TipoManutencao tipoManutencao;

    @Schema(
            description = "ID do carro que será submetido à manutenção",
            example = "1"
    )
    @NotNull(message = "O carro é obrigatório")
    private Long carro_id;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public Long getCarro_id() {
        return carro_id;
    }

    public void setCarro_id(Long carro_id) {
        this.carro_id = carro_id;
    }
}