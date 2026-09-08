package com.devs.locadora.carros.controllers;

import java.time.LocalDate;
import java.util.List;

import com.devs.locadora.carros.dto.CarroDTO;
import com.devs.locadora.carros.dto.CarroResponseDTO;
import com.devs.locadora.carros.dto.ErrorResponseDTO;
import com.devs.locadora.carros.services.CarroService;

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
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    CarroService carroService;

    @Operation(
            summary = "Cadastrar um carro",
            description = "Cadastra um novo carro na locadora"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Carro cadastrado com sucesso"
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
    public ResponseEntity<CarroResponseDTO> insert(@Valid @RequestBody CarroDTO carroDTO) {

        CarroResponseDTO carroResponseDTO = carroService.insert(carroDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(carroResponseDTO);
    }

    @Operation(
            summary = "Listar todos os carros",
            description = "Retorna todos os carros cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Carros encontrados com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> findAll() {

        List<CarroResponseDTO> carroResponseDTO = carroService.findAll();

        return ResponseEntity.ok(carroResponseDTO);
    }

    @Operation(
            summary = "Buscar carro por ID",
            description = "Retorna os dados de um carro específico"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Carro encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carro não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> findById(@PathVariable Long id) {

        CarroResponseDTO carroResponseDTO = carroService.findById(id);

        return ResponseEntity.ok(carroResponseDTO);
    }

    @Operation(
            summary = "Atualizar carro",
            description = "Atualiza os dados de um carro existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Carro atualizado com sucesso"
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
                    description = "Carro não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CarroDTO carroAtualizado) {

        CarroResponseDTO carroResponseDTO =
                carroService.update(id, carroAtualizado);

        return ResponseEntity.ok(carroResponseDTO);
    }

    @Operation(
            summary = "Excluir carro",
            description = "Remove um carro pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Carro excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carro não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        carroService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar carros disponíveis",
            description = "Retorna os carros disponíveis para um determinado período"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Carros disponíveis encontrados com sucesso"
    )
    @GetMapping("/disponiveis")
    public ResponseEntity<List<CarroResponseDTO>> findCarrosDisponiveis(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {

        List<CarroResponseDTO> carroResponseDTO =
                carroService.findCarrosDisponiveis(dataInicio, dataFim);

        return ResponseEntity.ok(carroResponseDTO);
    }
}