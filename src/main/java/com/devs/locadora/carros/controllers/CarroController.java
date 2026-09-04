package com.devs.locadora.carros.controllers;

import java.time.LocalDate;
import java.util.List;
import com.devs.locadora.carros.dto.CarroDTO;
import com.devs.locadora.carros.dto.CarroResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devs.locadora.carros.services.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroResponseDTO> insert(@Valid @RequestBody CarroDTO carroDTO) {

        CarroResponseDTO carroResponseDTO = carroService.insert(carroDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(carroResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> findAll() {

        List<CarroResponseDTO> carroResponseDTO = carroService.findAll();

        return ResponseEntity.ok(carroResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> findById(@PathVariable Long id) {
        CarroResponseDTO carroResponseDTO = carroService.findById(id);

        return ResponseEntity.ok(carroResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CarroDTO carroAtualizado) {
        CarroResponseDTO carroResponseDTO = carroService.update(id, carroAtualizado);

        return ResponseEntity.ok(carroResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        carroService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<CarroResponseDTO>> findCarrosDisponiveis(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        List<CarroResponseDTO> carroResponseDTO = carroService.findCarrosDisponiveis(dataInicio, dataFim);

        return ResponseEntity.ok(carroResponseDTO);
    }
}

