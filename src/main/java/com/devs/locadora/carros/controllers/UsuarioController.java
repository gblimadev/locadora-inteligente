package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.UsuarioDTO;
import com.devs.locadora.carros.dto.UsuarioResponseDTO;
import com.devs.locadora.carros.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Cadastrar um usuário",
            description = "Cadastra um novo usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> insert(
            @Valid @RequestBody UsuarioDTO usuarioDTO) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.insert(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @Operation(
            summary = "Listar todos os usuários",
            description = "Retorna todos os usuários cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuários encontrados com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {

        List<UsuarioResponseDTO> usuarioResponseDTO = usuarioService.findAll();

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(
            @PathVariable Long id) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO usuarioAtualizadoDTO) {

        UsuarioResponseDTO usuarioResponseDTO =
                usuarioService.update(id, usuarioAtualizadoDTO);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar usuário por CPF",
            description = "Retorna os dados de um usuário através do CPF"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioResponseDTO> findByCpf(
            @PathVariable String cpf) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findByCpf(cpf);

        return ResponseEntity.ok(usuarioResponseDTO);
    }
}