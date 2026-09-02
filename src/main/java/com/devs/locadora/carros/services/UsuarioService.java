package com.devs.locadora.carros.services;

import java.util.List;
import java.util.Optional;

import com.devs.locadora.carros.dto.UsuarioDTO;
import com.devs.locadora.carros.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Usuario;
import com.devs.locadora.carros.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO insert(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.getNome());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setNumeroCnh(usuarioDTO.getNumeroCnh());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());

        usuarioRepository.save(usuario);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setCpf(usuario.getCpf());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        usuarioResponseDTO.setNumeroCnh(usuario.getNumeroCnh());
        usuarioResponseDTO.setDataNascimento(usuario.getDataNascimento());

        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> findAll() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioResponseDTO> usuarioResponseDTOs = usuarios.stream().map(usuario -> {

            UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

            usuarioResponseDTO.setId(usuario.getId());
            usuarioResponseDTO.setNome(usuario.getNome());
            usuarioResponseDTO.setCpf(usuario.getCpf());
            usuarioResponseDTO.setTelefone(usuario.getTelefone());
            usuarioResponseDTO.setNumeroCnh(usuario.getNumeroCnh());
            usuarioResponseDTO.setDataNascimento(usuario.getDataNascimento());

            return usuarioResponseDTO;

        }).toList();

        return usuarioResponseDTOs;
    }
    
    public UsuarioResponseDTO findById(Long id) {

    	Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setCpf(usuario.getCpf());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        usuarioResponseDTO.setNumeroCnh(usuario.getNumeroCnh());
        usuarioResponseDTO.setDataNascimento(usuario.getDataNascimento());

        return usuarioResponseDTO;

    }
    
    public void delete(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
    
    public UsuarioResponseDTO update(Long id, UsuarioDTO usuarioAtualizadoDTO) {

        Usuario usuario = usuarioRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioAtualizadoDTO.getNome());
        usuario.setCpf(usuarioAtualizadoDTO.getCpf());
        usuario.setTelefone(usuarioAtualizadoDTO.getTelefone());
        usuario.setSenha(usuarioAtualizadoDTO.getSenha());
        usuario.setNumeroCnh(usuarioAtualizadoDTO.getNumeroCnh());
        usuario.setDataNascimento(usuarioAtualizadoDTO.getDataNascimento());

        usuarioRepository.save(usuario);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setCpf(usuario.getCpf());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        usuarioResponseDTO.setNumeroCnh(usuario.getNumeroCnh());
        usuarioResponseDTO.setDataNascimento(usuario.getDataNascimento());

        return usuarioResponseDTO;
    }
    
    public UsuarioResponseDTO findByCpf(String cpf) {
    	
    	Usuario usuario = usuarioRepository.findByCpf(cpf)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setCpf(usuario.getCpf());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        usuarioResponseDTO.setNumeroCnh(usuario.getNumeroCnh());
        usuarioResponseDTO.setDataNascimento(usuario.getDataNascimento());
    	
    	return usuarioResponseDTO;
    }
}
