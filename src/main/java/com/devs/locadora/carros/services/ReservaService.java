package com.devs.locadora.carros.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.devs.locadora.carros.dto.ReservaDTO;
import com.devs.locadora.carros.dto.ReservaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Carro;
import com.devs.locadora.carros.entities.Reserva;
import com.devs.locadora.carros.entities.Usuario;
import com.devs.locadora.carros.entities.enums.StatusReserva;
import com.devs.locadora.carros.repositories.CarroRepository;
import com.devs.locadora.carros.repositories.ManutencaoRepository;
import com.devs.locadora.carros.repositories.ReservaRepository;
import com.devs.locadora.carros.repositories.UsuarioRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	public ReservaResponseDTO insert(ReservaDTO reservaDTO) {

		if (reservaDTO.getDataInicio().isAfter(reservaDTO.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		Usuario usuario = usuarioRepository.findById(reservaDTO.getUsuario_id())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Carro carro = carroRepository.findById(reservaDTO.getCarro_id())
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		boolean conflito = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carro.getId(), reservaDTO.getDataFim(), reservaDTO.getDataInicio());

		if (conflito) {
			throw new RuntimeException("Carro já possui uma reserva nesse período");
		}

		boolean manutencao = manutencaoRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carro.getId(), reservaDTO.getDataFim(), reservaDTO.getDataInicio());

		if (manutencao) {
			throw new RuntimeException("Carro está em manutenção nesse período");
		}

		long quantidadeDias = ChronoUnit.DAYS.between(reservaDTO.getDataInicio(), reservaDTO.getDataFim());

		BigDecimal valorTotal = carro.getPrecoDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

		Reserva reserva = new Reserva();
		reserva.setDataInicio(reservaDTO.getDataInicio());
		reserva.setDataFim(reservaDTO.getDataFim());
		reserva.setValorTotal(valorTotal);
		reserva.setStatus(StatusReserva.PENDENTE);
		reserva.setUsuario(usuario);
		reserva.setCarro(carro);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public List<ReservaResponseDTO> findAll() {

		List<Reserva> reservas = reservaRepository.findAll();

		List<ReservaResponseDTO> reservaResponseDTOs = reservas.stream().map(reserva -> {

			ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
			reservaResponseDTO.setId(reserva.getId());
			reservaResponseDTO.setDataInicio(reserva.getDataInicio());
			reservaResponseDTO.setDataFim(reserva.getDataFim());
			reservaResponseDTO.setValorTotal(reserva.getValorTotal());
			reservaResponseDTO.setStatus(reserva.getStatus());
			reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
			reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

			return reservaResponseDTO;
		}).toList();
		return reservaResponseDTOs;
	}

	public ReservaResponseDTO findById(Long id) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public ReservaResponseDTO update(Long id, ReservaDTO reservaAtualizadaDTO) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() == StatusReserva.CANCELADA) {
			throw new RuntimeException("Não é possível alterar uma reserva cancelada");
		}

		if (reserva.getStatus() == StatusReserva.FINALIZADA) {
			throw new RuntimeException("Não é possível alterar uma reserva finalizada");
		}

		if (reserva.getStatus() == StatusReserva.EM_ANDAMENTO) {
			throw new RuntimeException("Não é possível alterar uma reserva em andamento");
		}

		Carro carro = reserva.getCarro();

		if (reservaAtualizadaDTO.getDataInicio().isAfter(reservaAtualizadaDTO.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflito = reservaRepository
				.existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carro.getId(), id,
						reservaAtualizadaDTO.getDataFim(), reservaAtualizadaDTO.getDataInicio());

		if (conflito) {
			throw new RuntimeException("Carro já possui uma reserva nesse período");
		}

		boolean manutencao = manutencaoRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carro.getId(), reservaAtualizadaDTO.getDataFim(), reservaAtualizadaDTO.getDataInicio());

		if (manutencao) {
			throw new RuntimeException("Carro está em manutenção nesse período");
		}

		long quantidadeDias = ChronoUnit.DAYS.between(reservaAtualizadaDTO.getDataInicio(),
				reservaAtualizadaDTO.getDataFim());

		BigDecimal valorTotal = carro.getPrecoDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

		reserva.setDataInicio(reservaAtualizadaDTO.getDataInicio());
		reserva.setDataFim(reservaAtualizadaDTO.getDataFim());
		reserva.setValorTotal(valorTotal);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public void deleteById(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		reservaRepository.delete(reserva);
	}

	public ReservaResponseDTO cancelar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() == StatusReserva.FINALIZADA) {
			throw new RuntimeException("Não é possível cancelar uma reserva finalizada");
		}

		if (reserva.getStatus() == StatusReserva.CANCELADA) {
			throw new RuntimeException("A reserva já está cancelada");
		}

		reserva.setStatus(StatusReserva.CANCELADA);

		Carro carro = reserva.getCarro();
		carro.setDisponivel(true);
		carroRepository.save(carro);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public ReservaResponseDTO confirmar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.PENDENTE) {
			throw new RuntimeException("Somente reservas pendentes podem ser confirmadas");
		}

		reserva.setStatus(StatusReserva.CONFIRMADA);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public ReservaResponseDTO iniciar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.CONFIRMADA) {
			throw new RuntimeException("Somente reservas confirmadas podem ser iniciadas");
		}

		if (LocalDate.now().isBefore(reserva.getDataInicio())) {
			throw new RuntimeException("A reserva ainda não pode ser iniciada");
		}

		if (LocalDate.now().isAfter(reserva.getDataFim())) {
			throw new RuntimeException("O período da reserva já terminou");
		}

		reserva.setStatus(StatusReserva.EM_ANDAMENTO);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return reservaResponseDTO;
	}

	public ReservaResponseDTO finalizar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.EM_ANDAMENTO) {
			throw new RuntimeException("Somente reservas em andamento podem ser finalizadas");
		}

		if (LocalDate.now().isBefore(reserva.getDataFim())) {
			throw new RuntimeException("A reserva ainda não pode ser finalizada");
		}

		reserva.setStatus(StatusReserva.FINALIZADA);

		reservaRepository.save(reserva);

		ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
		reservaResponseDTO.setId(reserva.getId());
		reservaResponseDTO.setDataInicio(reserva.getDataInicio());
		reservaResponseDTO.setDataFim(reserva.getDataFim());
		reservaResponseDTO.setValorTotal(reserva.getValorTotal());
		reservaResponseDTO.setStatus(reserva.getStatus());
		reservaResponseDTO.setUsuario_id(reserva.getUsuario().getId());
		reservaResponseDTO.setCarro_id(reserva.getCarro().getId());

		return  reservaResponseDTO;
	}
}
