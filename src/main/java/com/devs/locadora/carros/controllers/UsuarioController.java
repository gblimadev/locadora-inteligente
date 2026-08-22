package com.devs.locadora.carros.controllers;

import java.util.List;

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
    public Usuario insert(@RequestBody Usuario usuario) {
        return usuarioService.insert(usuario);
    }

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
    	return usuarioService.update(id, usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletebyId(@PathVariable Long id) {
    	
    	Usuario usuario = usuarioService.findById(id);
    	
        usuarioService.deleteById(usuario.getId());
    }

    @GetMapping("/cpf/{cpf}")
    public Usuario findByCpf(@PathVariable String cpf) {
        return usuarioService.findByCpf(cpf);
    }
}
