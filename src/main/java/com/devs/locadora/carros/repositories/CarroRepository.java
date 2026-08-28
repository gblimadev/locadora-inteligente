package com.devs.locadora.carros.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devs.locadora.carros.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	@Query("""
		    SELECT c FROM Carro c
		    WHERE c.id NOT IN (
		        SELECT r.carro.id
		        FROM Reserva r
		        WHERE r.dataInicio <= :dataFim
		        AND r.dataFim >= :dataInicio
		    )
		    AND c.id NOT IN (
		        SELECT m.carro.id
		        FROM Manutencao m
		        WHERE m.dataInicio <= :dataFim
		        AND m.dataFim >= :dataInicio
		    )
	""")
		List<Carro> findCarrosDisponiveis(
		        @Param("dataInicio") LocalDate dataInicio,
		        @Param("dataFim") LocalDate dataFim
		);
}
