package edu.fema.TrabalhoTopicosSpring.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoPontoEnum {

	E("Entrada"), S("Saída");

	@Getter
	private String tipo;

}
