package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ErrorResponseDTO;
import com.devs.locadora.carros.dto.ManutencaoDTO;
import com.devs.locadora.carros.dto.ManutencaoReponseDTO;
import com.devs.locadora.carros.entities.enums.StatusManutencao;
import com.devs.locadora.carros.services.ManutencaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    ManutencaoService manutencaoService;

    @Operation(
            summary = "Cadastrar uma manutenção",
            description = "Cadastra uma nova manutenção para um carro"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Manutenção cadastrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ManutencaoReponseDTO> insert(
            @Valid @RequestBody ManutencaoDTO manutencaoDTO) {

        ManutencaoReponseDTO manutencaoReponseDTO =
                manutencaoService.insert(manutencaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoReponseDTO);
    }

    @Operation(
            summary = "Listar todas as manutenções",
            description = "Retorna todas as manutenções cadastradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Manutenções encontradas com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<ManutencaoReponseDTO>> findAll() {

        List<ManutencaoReponseDTO> manutencaoReponseDTO =
                manutencaoService.findAll();

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @Operation(
            summary = "Buscar manutenção por ID",
            description = "Retorna os dados de uma manutenção específica"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Manutenção encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Manutenção não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ManutencaoReponseDTO> findById(@PathVariable Long id) {

        ManutencaoReponseDTO manutencaoReponseDTO =
                manutencaoService.findById(id);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @Operation(
            summary = "Atualizar manutenção",
            description = "Atualiza os dados de uma manutenção existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Manutenção atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Manutenção não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ManutencaoReponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ManutencaoDTO manutencaoAtualizadaDTO) {

        ManutencaoReponseDTO manutencaoReponseDTO =
                manutencaoService.update(id, manutencaoAtualizadaDTO);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @Operation(
            summary = "Excluir manutenção",
            description = "Remove uma manutenção pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Manutenção excluída com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Manutenção não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        manutencaoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualizar status da manutenção",
            description = "Atualiza o status de uma manutenção"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Status inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Manutenção não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<ManutencaoReponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusManutencao status) {

        ManutencaoReponseDTO manutencaoReponseDTO =
                manutencaoService.atualizarStatus(id, status);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }
}