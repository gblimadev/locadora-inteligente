package com.devs.locadora.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
