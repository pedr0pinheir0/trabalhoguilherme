package edu.fema.TrabalhoTopicosSpring.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (null != funcionario) {
			Integer rowCount = pontoRepository.countByFuncionario(funcionario);
			if (rowCount == 0) {
				ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now())
						.tipoPontoEnum(TipoPontoEnum.E).build());
			} else {
				TipoPontoEnum tipo;
				Ponto ultimoPonto = pontoRepository.findUltimoPontoByFuncionarioId(funcionario.getCodigo());
				tipo = ultimoPonto.getTipoPontoEnum().equals(TipoPontoEnum.E) ? TipoPontoEnum.S : TipoPontoEnum.E;
				ponto = pontoRepository.save(Ponto.builder().funcionario(funcionario).datahora(LocalDateTime.now())
						.tipoPontoEnum(tipo).build());
			}
		}
		return ponto;
	}

	public Map<String, Object> buscarTotalHoras(Long idFuncionario) {
		Map<String, Object> response = new HashMap<String, Object>();
		Funcionario funcionario = usuarioRepository.findById(idFuncionario).orElse(null);
		if (null == funcionario) {
			return null;
		} else {
			List<Ponto> pontos = pontoRepository.findByFuncionario(funcionario);
			if (pontos.size() <= 0) {
				return null;
			} else if (pontos.size() % 2 == 0) {
				Long total = 0L;
				for (int i = 0; i < pontos.size(); i++) {
					total += ChronoUnit.SECONDS.between(pontos.get(i).getDatahora(), pontos.get(i + 1).getDatahora());
					i++;
				}
				response.put("saldo", formatarTempo(Integer.parseInt(total.toString())));
				response.put("funcionario", funcionario);
				return response;
			} else {
				Long total = 0L;
				for (int i = 0; i < pontos.size() - 1; i++) {
					total += ChronoUnit.SECONDS.between(pontos.get(i).getDatahora(), pontos.get(i + 1).getDatahora());
					i++;
				}
				response.put("saldo", formatarTempo(Integer.parseInt(total.toString())));
				response.put("funcionario", funcionario);
				return response;
			}
		}
	}

	public String formatarTempo(int elapsed) {
		int ss = elapsed % 60;
		elapsed /= 60;
		int min = elapsed % 60;
		elapsed /= 60;
		int hh = elapsed % 24;
		return strzero(hh) + ":" + strzero(min) + ":" + strzero(ss);
	}

	private String strzero(int n) {
		if (n < 10)
			return "0" + String.valueOf(n);
		return String.valueOf(n);
	}
}
