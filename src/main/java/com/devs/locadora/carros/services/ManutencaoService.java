package com.devs.locadora.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.locadora.carros.entities.Manutencao;
import com.devs.locadora.carros.repositories.CarroRepository;
import com.devs.locadora.carros.repositories.ManutencaoRepository;
import com.devs.locadora.carros.repositories.ReservaRepository;
import com.devs.locadora.carros.repositories.UsuarioRepository;

@Service
public class ManutencaoService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ManutencaoRepository manutencaoRepository;
    

    public Manutencao insert(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> findAll() {
        return manutencaoRepository.findAll();
    }

    public Manutencao findById(Long id) {
        return manutencaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));
    }

    public Manutencao update(Long id, Manutencao manutencaoAtualizada) {

        Manutencao manutencao = manutencaoRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

        manutencao.setDescricao(manutencaoAtualizada.getDescricao());
        manutencao.setDataInicio(manutencaoAtualizada.getDataInicio());
        manutencao.setDataFim(manutencaoAtualizada.getDataFim());
        manutencao.setCusto(manutencaoAtualizada.getCusto());
        manutencao.setStatus(manutencaoAtualizada.getStatus());
        manutencao.setCarro(manutencaoAtualizada.getCarro());

        return manutencaoRepository.save(manutencao);
    }
    
    public void deleteById(Long id) {

        Manutencao manutencao = manutencaoRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));;;

        manutencaoRepository.delete(manutencao);
    }
}
