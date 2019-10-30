package edu.fema.TrabalhoTopicosSpring.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fema.TrabalhoTopicosSpring.model.Credencial;
import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.dto.NovoFuncionarioDTO;
import edu.fema.TrabalhoTopicosSpring.repository.CredencialRepository;
import edu.fema.TrabalhoTopicosSpring.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	CredencialRepository credencialRepository;

	public Funcionario salvarFuncionario(NovoFuncionarioDTO dto) {
		Funcionario funcionario = Funcionario.builder().nome(dto.getNome()).sexo(dto.getSexo()).cpf(dto.getCpf())
				.admissao(dto.getAdmissao()).build();
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
		Credencial credencial = Credencial.builder().nomeDeUsuario(dto.getNomeDeUsuario()).senha(dto.getSenha())
				.hash(gerarHash(dto.getSenha())).build();
		Credencial credencialSalva = credencialRepository.save(credencial);
		Funcionario funcionarioSalvoBanco = funcionarioRepository.findById(funcionarioSalvo.getCodigo()).orElse(null);
		funcionarioSalvoBanco.setCredencial(credencialSalva);

		return 	funcionarioRepository.save(funcionarioSalvoBanco);

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
