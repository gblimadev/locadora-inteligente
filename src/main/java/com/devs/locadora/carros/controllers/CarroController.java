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
}
