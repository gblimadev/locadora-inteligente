package com.devs.locadora.carros.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResponseDTO {

    @Schema(
            description = "Identificador único do usuário",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nome completo do usuário",
            example = "Gabriel Lima"
    )
    private String nome;

    @Schema(
            description = "CPF do usuário",
            example = "12345678901"
    )
    private String cpf;

    @Schema(
            description = "Telefone para contato do usuário",
            example = "81999999999"
    )
    private String telefone;

    @Schema(
            description = "Número da CNH do usuário",
            example = "12345678901"
    )
    private String numeroCnh;

    @Schema(
            description = "Data de nascimento do usuário",
            example = "2000-05-15"
    )
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }
}