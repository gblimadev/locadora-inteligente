package com.devs.locadora.carros.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devs.locadora.carros.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByCpf(String cpf);
}
