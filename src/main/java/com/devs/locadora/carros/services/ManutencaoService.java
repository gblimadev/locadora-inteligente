package com.devs.locadora.carros.services;

import java.math.BigDecimal;
import java.util.List;

import com.devs.locadora.carros.dto.ManutencaoDTO;
import com.devs.locadora.carros.dto.ManutencaoReponseDTO;
import com.devs.locadora.carros.entities.enums.StatusManutencao;
import com.devs.locadora.carros.entities.enums.TipoManutencao;
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

	public ManutencaoReponseDTO insert(ManutencaoDTO manutencaoDTO) {

		Carro carro = carroRepository.findById(manutencaoDTO.getCarro_id())
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		if (manutencaoDTO.getDataInicio().isAfter(manutencaoDTO.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflitoManutencao = manutencaoRepository
				.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carro.getId(), manutencaoDTO.getDataFim(),
						manutencaoDTO.getDataInicio());

		if (conflitoManutencao) {
			throw new RuntimeException("Carro já possui uma manutenção nesse período");
		}

		boolean conflitoReserva = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carro.getId(), manutencaoDTO.getDataFim(), manutencaoDTO.getDataInicio());

		if (conflitoReserva) {
			throw new RuntimeException("Carro possui uma reserva nesse período");
		}

		Manutencao manutencao = new Manutencao();
		manutencao.setDescricao(manutencaoDTO.getDescricao());
		manutencao.setDataInicio(manutencaoDTO.getDataInicio());
		manutencao.setDataFim(manutencaoDTO.getDataFim());
		manutencao.setTipoManutencao(manutencaoDTO.getTipoManutencao());
		manutencao.setStatus(StatusManutencao.PENDENTE);
		manutencao.setCarro(carro);

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.ELETRICA) {
			manutencao.setCusto(BigDecimal.valueOf(500));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.TROCA_DE_OLEO) {
			manutencao.setCusto(BigDecimal.valueOf(200));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.TROCA_DE_PNEU) {
			manutencao.setCusto(BigDecimal.valueOf(800));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.FREIO) {
			manutencao.setCusto(BigDecimal.valueOf(600));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.MOTOR) {
			manutencao.setCusto(BigDecimal.valueOf(2000));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.REVISAO) {
			manutencao.setCusto(BigDecimal.valueOf(400));
		}

		if (manutencaoDTO.getTipoManutencao() == TipoManutencao.OUTRA) {
			manutencao.setCusto(BigDecimal.valueOf(300));
		}

		 manutencaoRepository.save(manutencao);

		ManutencaoReponseDTO manutencaoReponseDTO = new ManutencaoReponseDTO();
		manutencaoReponseDTO.setId(manutencao.getId());
		manutencaoReponseDTO.setDescricao(manutencao.getDescricao());
		manutencaoReponseDTO.setDataInicio(manutencao.getDataInicio());
		manutencaoReponseDTO.setDataFim(manutencao.getDataFim());
		manutencaoReponseDTO.setStatus(manutencao.getStatus());
		manutencaoReponseDTO.setTipoManutencao(manutencao.getTipoManutencao());
		manutencaoReponseDTO.setCusto(manutencao.getCusto());
		manutencaoReponseDTO.setCarro_id(manutencao.getCarro().getId());

		return manutencaoReponseDTO;
	}

	public List<ManutencaoReponseDTO> findAll() {

		List<Manutencao> manutencoes = manutencaoRepository.findAll();

		List<ManutencaoReponseDTO> manutencaoReponseDTOs = manutencoes.stream().map(manutencao -> {

			ManutencaoReponseDTO manutencaoReponseDTO = new ManutencaoReponseDTO();
			manutencaoReponseDTO.setId(manutencao.getId());
			manutencaoReponseDTO.setDescricao(manutencao.getDescricao());
			manutencaoReponseDTO.setDataInicio(manutencao.getDataInicio());
			manutencaoReponseDTO.setDataFim(manutencao.getDataFim());
			manutencaoReponseDTO.setCusto(manutencao.getCusto());
			manutencaoReponseDTO.setStatus(manutencao.getStatus());
			manutencaoReponseDTO.setTipoManutencao(manutencao.getTipoManutencao());
			manutencaoReponseDTO.setCarro_id(manutencao.getCarro().getId());

			return manutencaoReponseDTO;
		}).toList();
		return manutencaoReponseDTOs;
	}

	public ManutencaoReponseDTO findById(Long id) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

		ManutencaoReponseDTO manutencaoReponseDTO = new ManutencaoReponseDTO();
		manutencaoReponseDTO.setId(manutencao.getId());
		manutencaoReponseDTO.setDescricao(manutencao.getDescricao());
		manutencaoReponseDTO.setDataInicio(manutencao.getDataInicio());
		manutencaoReponseDTO.setDataFim(manutencao.getDataFim());
		manutencaoReponseDTO.setCusto(manutencao.getCusto());
		manutencaoReponseDTO.setStatus(manutencao.getStatus());
		manutencaoReponseDTO.setTipoManutencao(manutencao.getTipoManutencao());
		manutencaoReponseDTO.setCarro_id(manutencao.getCarro().getId());

		return manutencaoReponseDTO;
	}

	public ManutencaoReponseDTO update(Long id, ManutencaoDTO manutencaoAtualizadaDTO) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

		Carro carro = manutencao.getCarro();

		if (manutencao.getStatus() == StatusManutencao.CONCLUIDA) {
			throw new RuntimeException("Não é possível alterar uma manutenção concluída");
		}

		if (manutencaoAtualizadaDTO.getDataInicio().isAfter(manutencaoAtualizadaDTO.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflitoManutencao = manutencaoRepository
				.existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carro.getId(), id,
						manutencaoAtualizadaDTO.getDataFim(), manutencaoAtualizadaDTO.getDataInicio());

		if (conflitoManutencao) {
			throw new RuntimeException("Carro já possui uma manutenção nesse período");
		}

		boolean conflitoReserva = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carro.getId(), manutencaoAtualizadaDTO.getDataFim(), manutencaoAtualizadaDTO.getDataInicio());

		if (conflitoReserva) {
			throw new RuntimeException("Carro possui uma reserva nesse período");
		}

		manutencao.setDescricao(manutencaoAtualizadaDTO.getDescricao());
		manutencao.setDataInicio(manutencaoAtualizadaDTO.getDataInicio());
		manutencao.setDataFim(manutencaoAtualizadaDTO.getDataFim());

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.ELETRICA) {
			manutencao.setCusto(BigDecimal.valueOf(500));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.TROCA_DE_OLEO) {
			manutencao.setCusto(BigDecimal.valueOf(200));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.TROCA_DE_PNEU) {
			manutencao.setCusto(BigDecimal.valueOf(800));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.FREIO) {
			manutencao.setCusto(BigDecimal.valueOf(600));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.MOTOR) {
			manutencao.setCusto(BigDecimal.valueOf(2000));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.REVISAO) {
			manutencao.setCusto(BigDecimal.valueOf(400));
		}

		if (manutencaoAtualizadaDTO.getTipoManutencao() == TipoManutencao.OUTRA) {
			manutencao.setCusto(BigDecimal.valueOf(300));
		}

		manutencao.setTipoManutencao(manutencaoAtualizadaDTO.getTipoManutencao());

		manutencaoRepository.save(manutencao);

		ManutencaoReponseDTO manutencaoReponseDTO = new ManutencaoReponseDTO();
		manutencaoReponseDTO.setId(manutencao.getId());
		manutencaoReponseDTO.setDescricao(manutencao.getDescricao());
		manutencaoReponseDTO.setDataInicio(manutencao.getDataInicio());
		manutencaoReponseDTO.setDataFim(manutencao.getDataFim());
		manutencaoReponseDTO.setCusto(manutencao.getCusto());
		manutencaoReponseDTO.setTipoManutencao(manutencao.getTipoManutencao());
		manutencaoReponseDTO.setCarro_id(manutencao.getCarro().getId());

		return manutencaoReponseDTO;
	}

	public void deleteById(Long id) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));
		
		manutencaoRepository.delete(manutencao);
	}

	public ManutencaoReponseDTO atualizarStatus(Long id, StatusManutencao novoStatus) {

		Manutencao manutencao = manutencaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

		if (manutencao.getStatus() == StatusManutencao.CONCLUIDA) {
			throw new RuntimeException("A manutenção já está concluída");
		}

		if (manutencao.getStatus() == StatusManutencao.PENDENTE
				&& novoStatus == StatusManutencao.CONCLUIDA) {
			throw new RuntimeException(
					"Não é possível concluir uma manutenção pendente");
		}

		manutencao.setStatus(novoStatus);

		manutencaoRepository.save(manutencao);

		ManutencaoReponseDTO manutencaoReponseDTO = new ManutencaoReponseDTO();

		manutencaoReponseDTO.setId(manutencao.getId());
		manutencaoReponseDTO.setDescricao(manutencao.getDescricao());
		manutencaoReponseDTO.setDataInicio(manutencao.getDataInicio());
		manutencaoReponseDTO.setDataFim(manutencao.getDataFim());
		manutencaoReponseDTO.setCusto(manutencao.getCusto());
		manutencaoReponseDTO.setStatus(manutencao.getStatus());
		manutencaoReponseDTO.setTipoManutencao(manutencao.getTipoManutencao());
		manutencaoReponseDTO.setCarro_id(manutencao.getCarro().getId());

		return manutencaoReponseDTO;
	}
}
