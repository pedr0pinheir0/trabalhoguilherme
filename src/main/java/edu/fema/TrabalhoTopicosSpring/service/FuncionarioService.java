package edu.fema.TrabalhoTopicosSpring.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fema.TrabalhoTopicosSpring.exception.ExceptionEnum;
import edu.fema.TrabalhoTopicosSpring.exception.TrabalhoException;
import edu.fema.TrabalhoTopicosSpring.model.Credencial;
import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.dto.CredencialDTO;
import edu.fema.TrabalhoTopicosSpring.model.dto.FuncionarioConsultaDTO;
import edu.fema.TrabalhoTopicosSpring.model.dto.NovoFuncionarioDTO;
import edu.fema.TrabalhoTopicosSpring.model.dto.PessoalDTO;
import edu.fema.TrabalhoTopicosSpring.model.enums.GeneroEnum;
import edu.fema.TrabalhoTopicosSpring.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<FuncionarioConsultaDTO> findAll() {
		return converterLista(funcionarioRepository.findAll());
	}

	public FuncionarioConsultaDTO funcionarioPorCodigo(Long codigo) {
		Funcionario funcionario = funcionarioRepository.findById(codigo).orElse(null);
		if (funcionario == null) {
			throw new TrabalhoException(ExceptionEnum.USUARIO_NULL);
		}
		return gerarObjetoDeConsultaDeFuncionario(funcionario);

	}

	public FuncionarioConsultaDTO salvarFuncionario(NovoFuncionarioDTO dto) {
		Integer rowCount = funcionarioRepository.countByCredencialNomeDeUsuario(dto.getNomeDeUsuario());
		if (rowCount.equals(1)) {
			throw new TrabalhoException(ExceptionEnum.USUARIO_JA_CADASTRADO);
		}
		Funcionario funcionario = converterFuncionarioDtoParaFuncionario(dto);
		funcionario.setCredencial(extrairCredenciaisDeDto(dto));
		funcionario.setSaldoDeHoras(0L);
		funcionarioRepository.save(funcionario);
		return gerarObjetoDeConsultaDeFuncionario(funcionario);

	}

	public Long buscarTotalDeSegundosPorFuncionario(Funcionario funcionario) {
		return funcionarioRepository.findSaldoDeHorasByCodigo(funcionario.getCodigo());
	}

	private List<FuncionarioConsultaDTO> converterLista(List<Funcionario> funcionarios) {
		List<FuncionarioConsultaDTO> novaLista = new ArrayList<FuncionarioConsultaDTO>();
		funcionarios.forEach(objeto -> {
			novaLista.add(gerarObjetoDeConsultaDeFuncionario(objeto));
		});
		return Collections.unmodifiableList(novaLista);
	}

	private FuncionarioConsultaDTO gerarObjetoDeConsultaDeFuncionario(Funcionario funcionario) {
		Long codigo = funcionario.getCodigo();
		Long horas = funcionario.getSaldoDeHoras();
		PessoalDTO pessoal = new PessoalDTO(funcionario.getNome(), funcionario.getGenero(), funcionario.getCpf());
		CredencialDTO credencial = new CredencialDTO(funcionario.getCredencial().getNomeDeUsuario(),
				funcionario.getCredencial().getSenha(), funcionario.getCredencial().getHash());
		String cargo = funcionario.getCargo();
		return FuncionarioConsultaDTO.builder().codigo(codigo).saldoDeHoras(horas).credenciais(credencial)
				.dadosPessoais(pessoal).cargo(cargo).build();
	}

	private Credencial extrairCredenciaisDeDto(NovoFuncionarioDTO dto) {
		return Credencial.builder().nomeDeUsuario(dto.getNomeDeUsuario()).senha(dto.getSenha())
				.hash(gerarHash(dto.getSenha())).build();
	}

	private Funcionario converterFuncionarioDtoParaFuncionario(NovoFuncionarioDTO dto) {
		return Funcionario.builder().nome(dto.getNome()).genero(validarGenero(dto.getGenero())).cpf(dto.getCpf())
				.admissao(dto.getAdmissao()).cargo(dto.getCargo()).build();
	}

	private GeneroEnum validarGenero(String generoInformado) {
		GeneroEnum genero;
		if ("M".equals(generoInformado)) {
			genero = GeneroEnum.M;
		} else if ("F".equals(generoInformado)) {
			genero = GeneroEnum.F;
		} else {
			genero = GeneroEnum.N;
		}
		return genero;
	}

	private String gerarHash(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			return messageDigest.toString();
		} catch (Exception e) {
			System.err.println("Senha Inválida!");
			return senha;
		}
	}

}
