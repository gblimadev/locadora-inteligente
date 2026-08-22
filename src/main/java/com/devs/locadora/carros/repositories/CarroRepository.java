package com.devs.locadora.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

}
