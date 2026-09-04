package com.devs.locadora.carros.dto;

import com.devs.locadora.carros.entities.enums.StatusManutencao;
import com.devs.locadora.carros.entities.enums.TipoManutencao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ManutencaoDTO {

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "A data início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data fim é obrigatória")
    private LocalDate dataFim;

    private TipoManutencao tipoManutencao;

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
