package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ManutencaoDTO;
import com.devs.locadora.carros.dto.ManutencaoReponseDTO;
import com.devs.locadora.carros.entities.enums.StatusManutencao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devs.locadora.carros.services.ManutencaoService;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    ManutencaoService manutencaoService;

    @PostMapping
    public ResponseEntity<ManutencaoReponseDTO> insert(@Valid @RequestBody ManutencaoDTO manutencaoDTO) {
        ManutencaoReponseDTO manutencaoReponseDTO = manutencaoService.insert(manutencaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoReponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ManutencaoReponseDTO>> findAll() {
        List<ManutencaoReponseDTO> manutencaoReponseDTO = manutencaoService.findAll();

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManutencaoReponseDTO> findById(@PathVariable Long id) {
        ManutencaoReponseDTO manutencaoReponseDTO = manutencaoService.findById(id);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManutencaoReponseDTO> update(@PathVariable Long id, @Valid @RequestBody ManutencaoDTO manutencaoAtualizadaDTO) {
        ManutencaoReponseDTO manutencaoReponseDTO = manutencaoService.update(id, manutencaoAtualizadaDTO);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        manutencaoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ManutencaoReponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusManutencao status) {
        ManutencaoReponseDTO manutencaoReponseDTO = manutencaoService.atualizarStatus(id, status);

        return ResponseEntity.ok(manutencaoReponseDTO);
    }
}
