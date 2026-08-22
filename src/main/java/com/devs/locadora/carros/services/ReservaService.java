package com.devs.locadora.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Reserva;
import com.devs.locadora.carros.repositories.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva insert(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }

    public Reserva update(Long id, Reserva reservaAtualizada) {

        Reserva reserva = reservaRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setDataInicio(reservaAtualizada.getDataInicio());
        reserva.setDataFim(reservaAtualizada.getDataFim());
        reserva.setValorTotal(reservaAtualizada.getValorTotal());
        reserva.setStatus(reservaAtualizada.getStatus());
        reserva.setUsuario(reservaAtualizada.getUsuario());
        reserva.setCarro(reservaAtualizada.getCarro());

        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {

        Reserva reserva = reservaRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservaRepository.delete(reserva);
    }
}
