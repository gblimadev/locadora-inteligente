package com.devs.locadora.carros.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Manutencao;
import org.springframework.data.jpa.repository.Query;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{
	boolean existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual( 
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
