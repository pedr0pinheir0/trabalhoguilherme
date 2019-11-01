package edu.fema.TrabalhoTopicosSpring.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fema.TrabalhoTopicosSpring.exception.ExceptionEnum;
import edu.fema.TrabalhoTopicosSpring.exception.TrabalhoException;
import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.Ponto;
import edu.fema.TrabalhoTopicosSpring.model.enums.TipoPontoEnum;
import edu.fema.TrabalhoTopicosSpring.repository.FuncionarioRepository;
import edu.fema.TrabalhoTopicosSpring.repository.PontoRepository;

@Service
public class PontoService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PontoRepository pontoRepository;

	public Ponto efetuarRegistro(Long idFuncionario) {
		Ponto ponto = null;
		Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElse(null);
		if (null != funcionario) {
			Integer rowCount = pontoRepository.countByFuncionario(funcionario);
			if (rowCount == 0) {
				ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now())
						.tipoPontoEnum(TipoPontoEnum.E).build());
			} else {
				TipoPontoEnum tipo;
				Ponto ultimoPonto = pontoRepository.findUltimoPontoByFuncionarioId(funcionario.getCodigo());
				tipo = ultimoPonto.getTipoPontoEnum().equals(TipoPontoEnum.E) ? TipoPontoEnum.S : TipoPontoEnum.E;
				if (tipo == TipoPontoEnum.E) {
					ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now())
							.tipoPontoEnum(tipo).build());
				} else {
					ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now())
							.tipoPontoEnum(TipoPontoEnum.S).build());
					atualizarTotalDeHoras(ponto, funcionario, ultimoPonto);
				}
			}
		} else {
			throw new TrabalhoException(ExceptionEnum.USUARIO_INEXISTENTE);
		}
		return ponto;
	}

	private void atualizarTotalDeHoras(Ponto ponto, Funcionario funcionario, Ponto ultimoPonto) {
		Long intervaloDeTempo = ChronoUnit.SECONDS.between(ultimoPonto.getDatahora(), ponto.getDatahora());
		funcionario.setSaldoDeHoras(funcionario.getSaldoDeHoras() + intervaloDeTempo);
		funcionarioRepository.save(funcionario);
	}

}
