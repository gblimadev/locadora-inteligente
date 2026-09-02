package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ManutencaoDTO;
import com.devs.locadora.carros.dto.ManutencaoReponseDTO;
import com.devs.locadora.carros.entities.enums.StatusManutencao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devs.locadora.carros.entities.Manutencao;
import com.devs.locadora.carros.services.ManutencaoService;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    ManutencaoService manutencaoService;

    @PostMapping
    public ManutencaoReponseDTO insert(@RequestBody ManutencaoDTO manutencaoDTO) {
        return manutencaoService.insert(manutencaoDTO);
    }

    @GetMapping
    public List<ManutencaoReponseDTO> findAll() {
        return manutencaoService.findAll();
    }

    @GetMapping("/{id}")
    public ManutencaoReponseDTO findById(@PathVariable Long id) {
        return manutencaoService.findById(id);
    }

    @PutMapping("/{id}")
    public ManutencaoReponseDTO update(@PathVariable Long id, @RequestBody ManutencaoDTO manutencaoAtualizadaDTO) {
        return manutencaoService.update(id, manutencaoAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        manutencaoService.deleteById(id);
    }

    @PutMapping("/{id}/status")
    public ManutencaoReponseDTO atualizarStatus( @PathVariable Long id, @RequestParam StatusManutencao status) {
        return manutencaoService.atualizarStatus(id, status);
    }
}
