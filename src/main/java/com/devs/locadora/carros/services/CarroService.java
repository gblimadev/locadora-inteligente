package com.devs.locadora.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.repositories.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Carro insert(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro findById(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public Carro update(Long id, Carro carroAtualizado) {

        Carro carro = carroRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Carro não encontrado"));;

        carro.setMarca(carroAtualizado.getMarca());
        carro.setModelo(carroAtualizado.getModelo());
        carro.setAno(carroAtualizado.getAno());
        carro.setTipo(carroAtualizado.getTipo());
        carro.setCombustivel(carroAtualizado.getCombustivel());
        carro.setCambio(carroAtualizado.getCambio());
        carro.setNivelDesempenho(carroAtualizado.getNivelDesempenho());
        carro.setNivelEconomia(carroAtualizado.getNivelEconomia());
        carro.setNivelConforto(carroAtualizado.getNivelConforto());
        carro.setLugares(carroAtualizado.getLugares());
        carro.setPortaMalas(carroAtualizado.getPortaMalas());
        carro.setPrecoDiaria(carroAtualizado.getPrecoDiaria());
        carro.setDisponivel(carroAtualizado.getDisponivel());

        return carroRepository.save(carro);
    }

    public void deleteById(Long id) {

        Carro carro = carroRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carroRepository.delete(carro);
    }
}
