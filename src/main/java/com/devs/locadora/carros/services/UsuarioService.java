package com.devs.locadora.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Usuario;
import com.devs.locadora.carros.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    public Usuario findById(Long id) {
    	return usuarioRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    
    public void deleteById(Long id) {
    	
    	Usuario usuario = usuarioRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    	
    	usuarioRepository.deleteById(usuario.getId());
    }
    
    public Usuario update(Long id, Usuario usuarioAtualizado) {

        Usuario usuario = usuarioRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setNumeroCnh(usuarioAtualizado.getNumeroCnh());
        usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());

        return usuarioRepository.save(usuario);
    }
    
    public Usuario findByCpf(String cpf) {
    	
    	Usuario usuario = usuarioRepository.findByCpf(cpf)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    	
    	return usuario;
    }
}
