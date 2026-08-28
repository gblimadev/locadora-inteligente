package com.devs.locadora.carros.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.services.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping
    public Carro insert(@RequestBody Carro carro) {
        return carroService.insert(carro);
    }

    @GetMapping
    public List<Carro> findAll() {
        return carroService.findAll();
    }

    @GetMapping("/{id}")
    public Carro findById(@PathVariable Long id) {
        return carroService.findById(id);
    }

    @PutMapping("/{id}")
    public Carro update(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        return carroService.update(id, carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        carroService.deleteById(id);
    }

    @GetMapping("/disponiveis")
    public List<Carro> findCarrosDisponiveis(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        return carroService.findCarrosDisponiveis(dataInicio, dataFim);
    }
}
