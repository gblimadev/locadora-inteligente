package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ErrorResponseDTO;
import com.devs.locadora.carros.dto.ReservaDTO;
import com.devs.locadora.carros.dto.ReservaResponseDTO;
import com.devs.locadora.carros.services.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Operation(
            summary = "Cadastrar uma reserva",
            description = "Cadastra uma nova reserva para um usuário e um carro"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Reserva cadastrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou regra de negócio violada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário ou carro não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> insert(
            @Valid @RequestBody ReservaDTO reservaDTO) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.insert(reservaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaResponseDTO);
    }

    @Operation(
            summary = "Listar todas as reservas",
            description = "Retorna todas as reservas cadastradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Reservas encontradas com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> findAll() {

        List<ReservaResponseDTO> reservaResponseDTOS =
                reservaService.findAll();

        return ResponseEntity.ok(reservaResponseDTOS);
    }

    @Operation(
            summary = "Buscar reserva por ID",
            description = "Retorna os dados de uma reserva específica"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> findById(
            @PathVariable Long id) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.findById(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @Operation(
            summary = "Atualizar reserva",
            description = "Atualiza os dados de uma reserva existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou regra de negócio violada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva, usuário ou carro não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ReservaDTO reservaAtualizadaDTO) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.update(id, reservaAtualizadaDTO);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @Operation(
            summary = "Excluir reserva",
            description = "Remove uma reserva pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Reserva excluída com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio impede a exclusão",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        reservaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Cancelar reserva",
            description = "Cancela uma reserva e libera o carro para novas reservas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva cancelada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Reserva não pode ser cancelada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelar(
            @PathVariable Long id) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.cancelar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @Operation(
            summary = "Confirmar reserva",
            description = "Confirma uma reserva"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva confirmada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Reserva não pode ser confirmada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ReservaResponseDTO> confirmar(
            @PathVariable Long id) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.confirmar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @Operation(
            summary = "Iniciar reserva",
            description = "Inicia uma reserva confirmada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva iniciada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Reserva não pode ser iniciada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}/iniciar")
    public ResponseEntity<ReservaResponseDTO> iniciar(
            @PathVariable Long id) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.iniciar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @Operation(
            summary = "Finalizar reserva",
            description = "Finaliza uma reserva em andamento"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserva finalizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Reserva não pode ser finalizada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserva não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ReservaResponseDTO> finalizar(
            @PathVariable Long id) {

        ReservaResponseDTO reservaResponseDTO =
                reservaService.finalizar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }
}