package com.devs.locadora.carros.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Manutencao;
import org.springframework.data.jpa.repository.Query;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{
	boolean existsByCarroIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual( // Existe uma manutenção para este carro cujo início seja menor ou igual ao fim da reserva e cujo fim seja maior ou igual ao início da reserva
            Long carroId,
            LocalDate dataFim,
            LocalDate dataInicio
    );
}
