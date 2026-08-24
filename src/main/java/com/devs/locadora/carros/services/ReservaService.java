package com.devs.locadora.carros.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

	public Reserva insert(Reserva reserva) {

		Long usuarioId = reserva.getUsuario().getId();
		Long carroId = reserva.getCarro().getId();

		if (reserva.getDataInicio().isAfter(reserva.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflito = reservaRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carroId, reserva.getDataFim(), reserva.getDataInicio());

		if (conflito) {
			throw new RuntimeException("Carro já possui uma reserva nesse período");
		}

		boolean manutencao = manutencaoRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carroId, reserva.getDataFim(), reserva.getDataInicio());

		if (manutencao) {
			throw new RuntimeException("Carro está em manutenção nesse período");
		}

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Carro carro = carroRepository.findById(carroId)
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		long quantidadeDias = ChronoUnit.DAYS.between(reserva.getDataInicio(), reserva.getDataFim());

		BigDecimal valorTotal = carro.getPrecoDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

		reserva.setValorTotal(valorTotal);

		reserva.setStatus(StatusReserva.PENDENTE);

		return reservaRepository.save(reserva);
	}

	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	public Reserva findById(Long id) {
		return reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
	}

	public Reserva update(Long id, Reserva reservaAtualizada) {

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

		Long usuarioId = reservaAtualizada.getUsuario().getId();
		Long carroId = reservaAtualizada.getCarro().getId();

		usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Carro carro = carroRepository.findById(carroId)
				.orElseThrow(() -> new RuntimeException("Carro não encontrado"));

		if (reservaAtualizada.getDataInicio().isAfter(reservaAtualizada.getDataFim())) {
			throw new RuntimeException("A data de início não pode ser posterior à data de fim");
		}

		boolean conflito = reservaRepository
				.existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(carroId, id,
						reservaAtualizada.getDataFim(), reservaAtualizada.getDataInicio());

		if (conflito) {
			throw new RuntimeException("Carro já possui uma reserva nesse período");
		}

		boolean manutencao = manutencaoRepository.existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
				carroId, reservaAtualizada.getDataFim(), reservaAtualizada.getDataInicio());

		if (manutencao) {
			throw new RuntimeException("Carro está em manutenção nesse período");
		}

		long quantidadeDias = ChronoUnit.DAYS.between(reservaAtualizada.getDataInicio(),
				reservaAtualizada.getDataFim());

		BigDecimal valorTotal = carro.getPrecoDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

		reserva.setDataInicio(reservaAtualizada.getDataInicio());
		reserva.setDataFim(reservaAtualizada.getDataFim());
		reserva.setUsuario(reservaAtualizada.getUsuario());
		reserva.setCarro(reservaAtualizada.getCarro());
		reserva.setValorTotal(valorTotal);

		return reservaRepository.save(reserva);
	}

	public void deleteById(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		reservaRepository.delete(reserva);
	}

	public Reserva cancelar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() == StatusReserva.FINALIZADA) {
			throw new RuntimeException("Não é possível cancelar uma reserva finalizada");
		}

		if (reserva.getStatus() == StatusReserva.CANCELADA) {
			throw new RuntimeException("A reserva já está cancelada");
		}

		reserva.setStatus(StatusReserva.CANCELADA);

		return reservaRepository.save(reserva);
	}

	public Reserva confirmar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.PENDENTE) {
			throw new RuntimeException("Somente reservas pendentes podem ser confirmadas");
		}

		reserva.setStatus(StatusReserva.CONFIRMADA);

		return reservaRepository.save(reserva);
	}

	public Reserva iniciar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.CONFIRMADA) {
			throw new RuntimeException("Somente reservas confirmadas podem ser iniciadas");
		}

		if (LocalDate.now().isBefore(reserva.getDataInicio())) {
			throw new RuntimeException("A reserva ainda não pode ser iniciada");
		}

		reserva.setStatus(StatusReserva.EM_ANDAMENTO);

		return reservaRepository.save(reserva);
	}

	public Reserva finalizar(Long id) {

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

		if (reserva.getStatus() != StatusReserva.EM_ANDAMENTO) {
			throw new RuntimeException("Somente reservas em andamento podem ser finalizadas");
		}

		if (LocalDate.now().isBefore(reserva.getDataFim())) {
			throw new RuntimeException("A reserva ainda não pode ser finalizada");
		}

		reserva.setStatus(StatusReserva.FINALIZADA);

		return reservaRepository.save(reserva);
	}
}
