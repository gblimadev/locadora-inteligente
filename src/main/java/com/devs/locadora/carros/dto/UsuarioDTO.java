package com.devs.locadora.carros.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    @Schema(
            description = "Nome completo do usuário",
            example = "Gabriel Lima"
    )
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Schema(
            description = "CPF do usuário, contendo 11 caracteres",
            example = "12345678901"
    )
    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    private String cpf;

    @Schema(
            description = "Telefone para contato do usuário",
            example = "81999999999"
    )
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @Schema(
            description = "Senha de acesso do usuário",
            example = "123456"
    )
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 4, max = 11, message = "A senha deve conter pelo menos 4 caracteres")
    private String senha;

    @Schema(
            description = "Número da CNH do usuário, contendo 11 caracteres",
            example = "12345678901"
    )
    @NotBlank(message = "O número da CNH é obrigatório")
    @Size(min = 11, max = 11, message = "O número da CNH deve conter 11 caracteres")
    private String numeroCnh;

    @Schema(
            description = "Data de nascimento do usuário",
            example = "2000-05-15"
    )
    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    // getters e setters
}