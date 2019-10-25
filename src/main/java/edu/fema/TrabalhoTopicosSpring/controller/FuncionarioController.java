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
import edu.fema.TrabalhoTopicosSpring.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class FuncionarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable @Valid Long codigo) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(codigo));
	}

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid Funcionario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}

}
