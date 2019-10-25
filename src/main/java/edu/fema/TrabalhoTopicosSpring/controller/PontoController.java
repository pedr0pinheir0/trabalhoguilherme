package edu.fema.TrabalhoTopicosSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.fema.TrabalhoTopicosSpring.model.Ponto;
import edu.fema.TrabalhoTopicosSpring.repository.PontoRepository;
import edu.fema.TrabalhoTopicosSpring.service.PontoService;

@RestController
@RequestMapping("/ponto")
public class PontoController {

	@Autowired
	private PontoRepository pontoRepository;
	
	@Autowired PontoService pontoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosOsPontos() {
		return ResponseEntity.status(HttpStatus.OK).body(pontoRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> efetuarRegistro(@RequestParam Long idFuncionario) {
		Ponto ponto = pontoService.efetuarRegistro(idFuncionario);
		if(ponto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ponto);
		}
	}

}
