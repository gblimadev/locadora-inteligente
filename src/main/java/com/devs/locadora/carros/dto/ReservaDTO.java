package com.devs.locadora.carros.dto;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.entities.Usuario;
import com.devs.locadora.carros.entities.enums.StatusReserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaDTO {

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal valorTotal;

    private Long usuario_id;

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
