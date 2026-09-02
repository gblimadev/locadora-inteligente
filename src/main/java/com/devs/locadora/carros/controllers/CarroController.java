package com.devs.locadora.carros.controllers;

import java.time.LocalDate;
import java.util.List;

import com.devs.locadora.carros.dto.CarroDTO;
import com.devs.locadora.carros.dto.CarroResponseDTO;
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
    public CarroResponseDTO insert(@RequestBody CarroDTO carroDTO) {
        return carroService.insert(carroDTO);
    }

    @GetMapping
    public List<CarroResponseDTO> findAll() {
        return carroService.findAll();
    }

    @GetMapping("/{id}")
    public CarroResponseDTO findById(@PathVariable Long id) {
        return carroService.findById(id);
    }

    @PutMapping("/{id}")
    public CarroResponseDTO update(@PathVariable Long id, @RequestBody CarroDTO carroAtualizado) {
        return carroService.update(id, carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        carroService.deleteById(id);
    }

    @GetMapping("/disponiveis")
    public List<CarroResponseDTO> findCarrosDisponiveis(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        return carroService.findCarrosDisponiveis(dataInicio, dataFim);
    }
}
