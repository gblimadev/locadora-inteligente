package com.devs.locadora.carros.controllers;

import java.util.List;

import com.devs.locadora.carros.dto.UsuarioDTO;
import com.devs.locadora.carros.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs.locadora.carros.entities.Usuario;
import com.devs.locadora.carros.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
    public UsuarioResponseDTO insert(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.insert(usuarioDTO);
    }

    @GetMapping
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }
    
    @GetMapping("/{id}")
    public UsuarioResponseDTO findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioAtualizadoDTO) {
    	return usuarioService.update(id, usuarioAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/cpf/{cpf}")
    public UsuarioResponseDTO findByCpf(@PathVariable String cpf) {
        return usuarioService.findByCpf(cpf);
    }
}
