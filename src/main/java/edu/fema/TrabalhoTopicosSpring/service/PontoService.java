package edu.fema.TrabalhoTopicosSpring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.Ponto;
import edu.fema.TrabalhoTopicosSpring.model.enums.TipoPontoEnum;
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
		Funcionario funcionario = usuarioRepository.findById(idFuncionario).orElse(null);
		if(null != funcionario){
			Integer rowCount = pontoRepository.countByFuncionario(funcionario);
			if(rowCount == 0) {
				ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now()).tipoPontoEnum(TipoPontoEnum.E).build());
			} else {
				TipoPontoEnum tipo;
				Ponto ultimoPonto = pontoRepository.findUltimoPontoByFuncionarioId(funcionario.getCodigo());
				tipo = ultimoPonto.getTipoPontoEnum().equals(TipoPontoEnum.E) ? TipoPontoEnum.S : TipoPontoEnum.E;
				ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now()).tipoPontoEnum(tipo).build());
			}
		}
		return ponto;
	}	
}
