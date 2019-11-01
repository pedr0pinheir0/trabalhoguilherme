package edu.fema.TrabalhoTopicosSpring.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GeneroEnum {

	M("Masculino"), F("Feminino"), N("Não informado");

	@Getter
	private String genero;

}
