package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.ReservaDTO;
import com.devs.locadora.carros.dto.ReservaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs.locadora.carros.entities.Reserva;
import com.devs.locadora.carros.services.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping
    public ReservaResponseDTO insert(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.insert(reservaDTO);
    }

    @GetMapping
    public List<ReservaResponseDTO> findAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ReservaResponseDTO findById(@PathVariable Long id) {
        return reservaService.findById(id);
    }

    @PutMapping("/{id}")
    public ReservaResponseDTO update(@PathVariable Long id, @RequestBody ReservaDTO reservaAtualizadaDTO) {
        return reservaService.update(id, reservaAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservaService.deleteById(id);
    }
    
    @PutMapping("/{id}/cancelar")
    public ReservaResponseDTO cancelar(@PathVariable Long id) {
        return reservaService.cancelar(id);
    }
    
    @PutMapping("/{id}/confirmar")
    public ReservaResponseDTO confirmar(@PathVariable Long id) {
        return reservaService.confirmar(id);
    }
    
    @PutMapping("/{id}/iniciar")
    public ReservaResponseDTO iniciar(@PathVariable Long id) {
        return reservaService.iniciar(id);
    }
    
    @PutMapping("/{id}/finalizar")
    public ReservaResponseDTO finalizar(@PathVariable Long id) {
        return reservaService.finalizar(id);
    }
}
