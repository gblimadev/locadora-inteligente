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

import com.devs.locadora.carros.entities.Reserva;
import com.devs.locadora.carros.services.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping
    public Reserva insert(@RequestBody Reserva reserva) {
        return reservaService.insert(reserva);
    }

    @GetMapping
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public Reserva findById(@PathVariable Long id) {
        return reservaService.findById(id);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        return reservaService.update(id, reservaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservaService.deleteById(id);
    }
    
    @PutMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable Long id) {
        return reservaService.cancelar(id);
    }
    
    @PutMapping("/{id}/confirmar")
    public Reserva confirmar(@PathVariable Long id) {
        return reservaService.confirmar(id);
    }
    
    @PutMapping("/{id}/iniciar")
    public Reserva iniciar(@PathVariable Long id) {
        return reservaService.iniciar(id);
    }
    
    @PutMapping("/{id}/finalizar")
    public Reserva finalizar(@PathVariable Long id) {
        return reservaService.finalizar(id);
    }
}
