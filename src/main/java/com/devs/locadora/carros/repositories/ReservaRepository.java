package com.devs.locadora.carros.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	boolean existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual( // existe uma reserva para esse carro cujo período se sobrepõe ao período que estou tentando reservar?
	        Long carroId,
	        LocalDate dataFim,
	        LocalDate dataInicio
	);

	boolean existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
			Long carroId, 
			Long id,
			LocalDate dataFim,
			LocalDate dataInicio);
}
