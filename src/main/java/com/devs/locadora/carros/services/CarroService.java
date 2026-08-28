package com.devs.locadora.carros.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Carro> findCarrosDisponiveis(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("A data início não pode ser posterior a data fim");
        }
        return carroRepository.findCarrosDisponiveis(dataInicio, dataFim);
    }
    public List<Carro> recomendarCarros(LocalDate dataInicio,
                                        LocalDate dataFim,
                                        String tipo,
                                        String modelo,
                                        String marca,
                                        String combustivel,
                                        String cambio,
                                        String nivelDesempenho,
                                        String nivelEconomia,
                                        String nivelConforto) {

        List<Carro> carros = carroRepository.findCarrosDisponiveis(dataInicio, dataFim);

        Map<Carro, Integer> pontuacoes = new HashMap<>();

        for (Carro carro: carros) {
            int pontuacao = 0;

            if (tipo != null && tipo.equals(carro.getTipo())) {
                pontuacao++;
            }

            if (modelo != null && modelo.equals(carro.getModelo())) {
                pontuacao++;
            }

            if (marca != null && marca.equals(carro.getMarca())) {
                pontuacao++;
            }

            if (combustivel != null && combustivel.equals(carro.getCombustivel())) {
                pontuacao++;
            }

            if (cambio != null && cambio.equals(carro.getCambio())) {
                pontuacao++;
            }

            if (nivelDesempenho != null && nivelDesempenho.equals(carro.getNivelDesempenho())) {
                pontuacao++;
            }

            if (nivelEconomia != null && nivelEconomia.equals(carro.getNivelEconomia())) {
                pontuacao++;
            }

            if (nivelConforto != null && nivelConforto.equals(carro.getNivelConforto())) {
                pontuacao++;
            }
            pontuacoes.put(carro, pontuacao);
        }
        return pontuacoes.entrySet()
                .stream()
                .sorted(Map.Entry.<Carro, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }
}
