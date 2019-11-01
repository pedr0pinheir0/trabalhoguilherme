package edu.fema.TrabalhoTopicosSpring.model.dto;

import edu.fema.TrabalhoTopicosSpring.model.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PessoalDTO {

	private String nome;
	private GeneroEnum genero;
	private String cpf;
	
	
	public String getGenero() {
		return genero.getGenero();
	}
}
