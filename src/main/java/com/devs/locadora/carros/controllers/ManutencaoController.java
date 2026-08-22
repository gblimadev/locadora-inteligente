package com.devs.locadora.carros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs.locadora.carros.entities.Manutencao;
import com.devs.locadora.carros.services.ManutencaoService;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    ManutencaoService manutencaoService;

    @PostMapping
    public Manutencao insert(@RequestBody Manutencao manutencao) {
        return manutencaoService.insert(manutencao);
    }

    @GetMapping
    public List<Manutencao> findAll() {
        return manutencaoService.findAll();
    }

    @GetMapping("/{id}")
    public Manutencao findById(@PathVariable Long id) {
        return manutencaoService.findById(id);
    }

    @PutMapping("/{id}")
    public Manutencao update(@PathVariable Long id, @RequestBody Manutencao manutencaoAtualizada) {

        return manutencaoService.update(id, manutencaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        manutencaoService.deleteById(id);
    }
}
