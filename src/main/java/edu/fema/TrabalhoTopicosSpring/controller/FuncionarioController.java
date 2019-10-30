package edu.fema.TrabalhoTopicosSpring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.dto.NovoFuncionarioDTO;
import edu.fema.TrabalhoTopicosSpring.repository.FuncionarioRepository;
import edu.fema.TrabalhoTopicosSpring.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAll());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable @Valid Long codigo) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findById(codigo));
	}

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid NovoFuncionarioDTO funcionario) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.salvarFuncionario(funcionario));
	}

}
