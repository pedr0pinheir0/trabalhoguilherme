package edu.fema.TrabalhoTopicosSpring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fema.TrabalhoTopicosSpring.model.Ponto;
import edu.fema.TrabalhoTopicosSpring.model.Usuario;
import edu.fema.TrabalhoTopicosSpring.repository.PontoRepository;
import edu.fema.TrabalhoTopicosSpring.repository.UsuarioRepository;

@Service
public class PontoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PontoRepository pontoRepository;
	
	public Ponto efetuarRegistro(Long idFuncionario) {
		Ponto ponto = null;
		Usuario usuario = usuarioRepository.findById(idFuncionario).orElse(null);
		if(null != usuario){
			ponto = pontoRepository.save(Ponto.builder().usuario(usuario).datahora(LocalDateTime.now()).build());
		}
		return ponto;
	}	
}
