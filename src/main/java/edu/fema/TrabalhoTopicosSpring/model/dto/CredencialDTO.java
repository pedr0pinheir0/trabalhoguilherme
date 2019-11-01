package edu.fema.TrabalhoTopicosSpring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredencialDTO {
	
	private String nomeDeUsuario;
	private String senha;
	private String hash;

}
