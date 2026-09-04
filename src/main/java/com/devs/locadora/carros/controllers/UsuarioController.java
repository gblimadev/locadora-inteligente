package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.UsuarioDTO;
import com.devs.locadora.carros.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs.locadora.carros.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> insert(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.insert(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {

        List<UsuarioResponseDTO> usuarioResponseDTO = usuarioService.findAll();

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioAtualizadoDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.update(id, usuarioAtualizadoDTO);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioResponseDTO> findByCpf(@PathVariable String cpf) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findByCpf(cpf);

        return ResponseEntity.ok(usuarioResponseDTO);
    }
}
