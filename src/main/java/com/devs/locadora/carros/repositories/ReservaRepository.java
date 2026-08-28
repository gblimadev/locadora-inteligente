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

	boolean existsByCarroIdAndIdNotAndDataInicioLessThanEqualAndDataFimGreaterThanEqual( // existe outra manutenção, para esse carro, que não seja a manutenção que estou editando, e cujo período se sobreponha ao novo período
			Long carroId, 
			Long id,
			LocalDate dataFim,
			LocalDate dataInicio);
}
