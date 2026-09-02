package com.devs.locadora.carros.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devs.locadora.carros.dto.CarroDTO;
import com.devs.locadora.carros.dto.CarroResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.repositories.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroResponseDTO insert(CarroDTO carroDTO) {

        Carro carro = new Carro();
        carro.setMarca(carroDTO.getMarca());
        carro.setModelo(carroDTO.getModelo());
        carro.setAno(carroDTO.getAno());
        carro.setTipo(carroDTO.getTipo());
        carro.setCombustivel(carroDTO.getCombustivel());
        carro.setCambio(carroDTO.getCambio());
        carro.setNivelDesempenho(carroDTO.getNivelDesempenho());
        carro.setNivelEconomia(carroDTO.getNivelEconomia());
        carro.setNivelConforto(carroDTO.getNivelConforto());
        carro.setLugares(carroDTO.getLugares());
        carro.setPortaMalas(carroDTO.getPortaMalas());
        carro.setPrecoDiaria(carroDTO.getPrecoDiaria());
        carro.setDisponivel(carroDTO.getDisponivel());

        carroRepository.save(carro);

        CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
        carroResponseDTO.setId(carro.getId());
        carroResponseDTO.setMarca(carro.getMarca());
        carroResponseDTO.setModelo(carro.getModelo());
        carroResponseDTO.setAno(carro.getAno());
        carroResponseDTO.setTipo(carro.getTipo());
        carroResponseDTO.setCombustivel(carro.getCombustivel());
        carroResponseDTO.setCambio(carro.getCambio());
        carroResponseDTO.setNivelDesempenho(carro.getNivelDesempenho());
        carroResponseDTO.setNivelEconomia(carro.getNivelEconomia());
        carroResponseDTO.setNivelConforto(carro.getNivelConforto());
        carroResponseDTO.setLugares(carro.getLugares());
        carroResponseDTO.setPortaMalas(carro.getPortaMalas());
        carroResponseDTO.setPrecoDiaria(carro.getPrecoDiaria());
        carroResponseDTO.setDisponivel(carro.getDisponivel());

        return carroResponseDTO;
    }

    public List<CarroResponseDTO> findAll() {

        List<Carro> carros = carroRepository.findAll();

        List<CarroResponseDTO> carroResponseDTOs = carros.stream().map(carro -> {

            CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
            carroResponseDTO.setId(carro.getId());
            carroResponseDTO.setMarca(carro.getMarca());
            carroResponseDTO.setModelo(carro.getModelo());
            carroResponseDTO.setAno(carro.getAno());
            carroResponseDTO.setTipo(carro.getTipo());
            carroResponseDTO.setCombustivel(carro.getCombustivel());
            carroResponseDTO.setCambio(carro.getCambio());
            carroResponseDTO.setNivelDesempenho(carro.getNivelDesempenho());
            carroResponseDTO.setNivelEconomia(carro.getNivelEconomia());
            carroResponseDTO.setNivelConforto(carro.getNivelConforto());
            carroResponseDTO.setLugares(carro.getLugares());
            carroResponseDTO.setPortaMalas(carro.getPortaMalas());
            carroResponseDTO.setPrecoDiaria(carro.getPrecoDiaria());
            carroResponseDTO.setDisponivel(carro.getDisponivel());

            return carroResponseDTO;
        }).toList();
        return carroResponseDTOs;
    }

    public CarroResponseDTO findById(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
        carroResponseDTO.setId(carro.getId());
        carroResponseDTO.setMarca(carro.getMarca());
        carroResponseDTO.setModelo(carro.getModelo());
        carroResponseDTO.setAno(carro.getAno());
        carroResponseDTO.setTipo(carro.getTipo());
        carroResponseDTO.setCombustivel(carro.getCombustivel());
        carroResponseDTO.setCambio(carro.getCambio());
        carroResponseDTO.setNivelDesempenho(carro.getNivelDesempenho());
        carroResponseDTO.setNivelEconomia(carro.getNivelEconomia());
        carroResponseDTO.setNivelConforto(carro.getNivelConforto());
        carroResponseDTO.setLugares(carro.getLugares());
        carroResponseDTO.setPortaMalas(carro.getPortaMalas());
        carroResponseDTO.setPrecoDiaria(carro.getPrecoDiaria());
        carroResponseDTO.setDisponivel(carro.getDisponivel());

        return carroResponseDTO;
    }

    public CarroResponseDTO update(Long id, CarroDTO carroAtualizadoDTO) {

        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carro.setMarca(carroAtualizadoDTO.getMarca());
        carro.setModelo(carroAtualizadoDTO.getModelo());
        carro.setAno(carroAtualizadoDTO.getAno());
        carro.setTipo(carroAtualizadoDTO.getTipo());
        carro.setCombustivel(carroAtualizadoDTO.getCombustivel());
        carro.setCambio(carroAtualizadoDTO.getCambio());
        carro.setNivelDesempenho(carroAtualizadoDTO.getNivelDesempenho());
        carro.setNivelEconomia(carroAtualizadoDTO.getNivelEconomia());
        carro.setNivelConforto(carroAtualizadoDTO.getNivelConforto());
        carro.setLugares(carroAtualizadoDTO.getLugares());
        carro.setPortaMalas(carroAtualizadoDTO.getPortaMalas());
        carro.setPrecoDiaria(carroAtualizadoDTO.getPrecoDiaria());
        carro.setDisponivel(carroAtualizadoDTO.getDisponivel());

        carroRepository.save(carro);

        CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
        carroResponseDTO.setId(carro.getId());
        carroResponseDTO.setMarca(carro.getMarca());
        carroResponseDTO.setModelo(carro.getModelo());
        carroResponseDTO.setAno(carro.getAno());
        carroResponseDTO.setTipo(carro.getTipo());
        carroResponseDTO.setCombustivel(carro.getCombustivel());
        carroResponseDTO.setCambio(carro.getCambio());
        carroResponseDTO.setNivelDesempenho(carro.getNivelDesempenho());
        carroResponseDTO.setNivelEconomia(carro.getNivelEconomia());
        carroResponseDTO.setNivelConforto(carro.getNivelConforto());
        carroResponseDTO.setLugares(carro.getLugares());
        carroResponseDTO.setPortaMalas(carro.getPortaMalas());
        carroResponseDTO.setPrecoDiaria(carro.getPrecoDiaria());
        carroResponseDTO.setDisponivel(carro.getDisponivel());

        return carroResponseDTO;
    }

    public void deleteById(Long id) {

        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carroRepository.delete(carro);
    }

    public List<CarroResponseDTO> findCarrosDisponiveis(LocalDate dataInicio, LocalDate dataFim) {

        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("A data início não pode ser posterior a data fim");
        }

        List<Carro> carros = carroRepository.findCarrosDisponiveis(dataInicio, dataFim);

        List<CarroResponseDTO> carroResponseDTOs = carros.stream().map(carro -> {

            CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
            carroResponseDTO.setId(carro.getId());
            carroResponseDTO.setMarca(carro.getMarca());
            carroResponseDTO.setModelo(carro.getModelo());
            carroResponseDTO.setAno(carro.getAno());
            carroResponseDTO.setTipo(carro.getTipo());
            carroResponseDTO.setCombustivel(carro.getCombustivel());
            carroResponseDTO.setCambio(carro.getCambio());
            carroResponseDTO.setNivelDesempenho(carro.getNivelDesempenho());
            carroResponseDTO.setNivelEconomia(carro.getNivelEconomia());
            carroResponseDTO.setNivelConforto(carro.getNivelConforto());
            carroResponseDTO.setLugares(carro.getLugares());
            carroResponseDTO.setPortaMalas(carro.getPortaMalas());
            carroResponseDTO.setPrecoDiaria(carro.getPrecoDiaria());
            carroResponseDTO.setDisponivel(carro.getDisponivel());

            return carroResponseDTO;
        }).toList();
        return carroResponseDTOs;
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

        for (Carro carro : carros) {
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
