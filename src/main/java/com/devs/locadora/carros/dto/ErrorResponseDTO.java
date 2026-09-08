package com.devs.locadora.carros.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorResponseDTO {

    @Schema(
            description = "Data e hora em que o erro ocorreu",
            example = "2026-09-08T02:30:00"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "Código HTTP do erro",
            example = "404"
    )
    private Integer status;

    @Schema(
            description = "Descrição do tipo do erro HTTP",
            example = "Not Found"
    )
    private String error;

    @Schema(
            description = "Mensagem detalhada explicando o motivo do erro",
            example = "Carro não encontrado"
    )
    private String message;

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(LocalDateTime timestamp, Integer status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}