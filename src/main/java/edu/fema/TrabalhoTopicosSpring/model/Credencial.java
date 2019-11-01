package edu.fema.TrabalhoTopicosSpring.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Credencial {

	@NotNull
	@Size(min = 1, max = 15)
	private String nomeDeUsuario;

	@NotNull
	@Size(min = 1, max = 15)
	private String senha;

	@NotNull
	private String hash;
}
