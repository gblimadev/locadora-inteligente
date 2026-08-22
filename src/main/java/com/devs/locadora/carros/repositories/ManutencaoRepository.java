package com.devs.locadora.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{

}
