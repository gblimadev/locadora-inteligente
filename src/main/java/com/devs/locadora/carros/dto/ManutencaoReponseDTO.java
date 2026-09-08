package com.devs.locadora.carros.dto;

import com.devs.locadora.carros.entities.enums.StatusManutencao;
import com.devs.locadora.carros.entities.enums.TipoManutencao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ManutencaoReponseDTO {

    @Schema(
            description = "Identificador único da manutenção",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Descrição da manutenção realizada no carro",
            example = "Troca de óleo e filtros"
    )
    private String descricao;

    @Schema(
            description = "Data de início da manutenção",
            example = "2026-09-10"
    )
    private LocalDate dataInicio;

    @Schema(
            description = "Data de término da manutenção",
            example = "2026-09-12"
    )
    private LocalDate dataFim;

    @Schema(
            description = "Custo total da manutenção",
            example = "350.00"
    )
    private BigDecimal custo;

    @Schema(
            description = "Status atual da manutenção",
            example = "EM_ANDAMENTO"
    )
    private StatusManutencao status;

    @Schema(
            description = "Tipo da manutenção realizada",
            example = "PREVENTIVA"
    )
    private TipoManutencao tipoManutencao;

    @Schema(
            description = "ID do carro relacionado à manutenção",
            example = "1"
    )
    private Long carro_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public StatusManutencao getStatus() {
        return status;
    }

    public void setStatus(StatusManutencao status) {
        this.status = status;
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