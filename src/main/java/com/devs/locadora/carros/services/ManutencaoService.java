package com.devs.locadora.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.entities.Manutencao;
import com.devs.locadora.carros.repositories.CarroRepository;
import com.devs.locadora.carros.repositories.ManutencaoRepository;
import com.devs.locadora.carros.repositories.ReservaRepository;
import com.devs.locadora.carros.repositories.UsuarioRepository;

@Service
public class ManutencaoService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	public Manutencao insert(Manutencao manutencao) {

		Long carroId = manutencao.getCarro().getId();

		Carro carro = carroRepository.findById(carroId).orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		if (manutencao.getDataInicio().isAfter(manutencao.getDataFim())) {

			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}
		boolean conflitoManutencao = manutencaoRepository
				.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carroId, manutencao.getDataFim(),
						manutencao.getDataInicio());

		if (conflitoManutencao) {

			throw new RuntimeException("Carro já possui uma manutenção nesse período");
		}

		boolean conflitoReserva = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carroId, manutencao.getDataFim(), manutencao.getDataInicio());

		if (conflitoReserva) {

			throw new RuntimeException("Carro possui uma reserva nesse período");
		}

		manutencao.setCarro(carro);

		return manutencaoRepository.save(manutencao);
	}

	public List<Manutencao> findAll() {
		return manutencaoRepository.findAll();
	}

	public Manutencao findById(Long id) {
		return manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));
	}

	public Manutencao update(Long id, Manutencao manutencaoAtualizada) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutencao não encontrada"));

		Long carroId = manutencaoAtualizada.getCarro().getId();

		Carro carro = carroRepository.findById(carroId)
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		if (manutencaoAtualizada.getDataInicio().isAfter(manutencaoAtualizada.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflitoManutencao = manutencaoRepository
				.existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carroId, id,
						manutencaoAtualizada.getDataFim(), manutencaoAtualizada.getDataInicio());

		if (conflitoManutencao) {
			throw new RuntimeException("Carro já possui uma manutenção nesse período");
		}

		boolean conflitoReserva = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carroId, manutencaoAtualizada.getDataFim(), manutencaoAtualizada.getDataInicio());

		if (conflitoReserva) {

			throw new RuntimeException("Carro possui uma reserva nesse período");
		}

		manutencao.setCarro(manutencaoAtualizada.getCarro());

		manutencao.setDataInicio(manutencaoAtualizada.getDataInicio());

		manutencao.setDataFim(manutencaoAtualizada.getDataFim());

		return manutencaoRepository.save(manutencao);
	}

	public void deleteById(Long id) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));
		
		manutencaoRepository.delete(manutencao);
	}
}
