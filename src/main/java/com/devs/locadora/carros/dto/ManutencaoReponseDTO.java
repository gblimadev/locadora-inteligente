package com.devs.locadora.carros.dto;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.entities.enums.StatusManutencao;
import com.devs.locadora.carros.entities.enums.TipoManutencao;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ManutencaoReponseDTO {

    private Long id;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal custo;

    private StatusManutencao status;

    private TipoManutencao tipoManutencao;

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
