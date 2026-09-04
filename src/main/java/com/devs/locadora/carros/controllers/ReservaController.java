package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ReservaDTO;
import com.devs.locadora.carros.dto.ReservaResponseDTO;
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

import com.devs.locadora.carros.services.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> insert(@Valid @RequestBody ReservaDTO reservaDTO) {
        ReservaResponseDTO reservaResponseDTO = reservaService.insert(reservaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> findAll() {
        List<ReservaResponseDTO> reservaResponseDTOS = reservaService.findAll();

        return ResponseEntity.ok(reservaResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> findById(@PathVariable Long id) {
        ReservaResponseDTO reservaResponseDTO = reservaService.findById(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> update(@PathVariable Long id,@Valid @RequestBody ReservaDTO reservaAtualizadaDTO) {
        ReservaResponseDTO reservaResponseDTO = reservaService.update(id, reservaAtualizadaDTO);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reservaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelar(@PathVariable Long id) {
        ReservaResponseDTO reservaResponseDTO = reservaService.cancelar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ReservaResponseDTO> confirmar(@PathVariable Long id) {
        ReservaResponseDTO reservaResponseDTO = reservaService.confirmar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<ReservaResponseDTO> iniciar(@PathVariable Long id) {
        ReservaResponseDTO reservaResponseDTO = reservaService.iniciar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ReservaResponseDTO> finalizar(@PathVariable Long id) {
        ReservaResponseDTO reservaResponseDTO = reservaService.finalizar(id);

        return ResponseEntity.ok(reservaResponseDTO);
    }
}
