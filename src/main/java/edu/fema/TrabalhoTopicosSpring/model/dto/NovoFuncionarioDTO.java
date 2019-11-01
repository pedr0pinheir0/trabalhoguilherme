package edu.fema.TrabalhoTopicosSpring.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NovoFuncionarioDTO {

	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	@NotNull
	private String genero;

	@NotNull
	private String nomeDeUsuario;

	@NotNull
	private String senha;

	@NotNull
	private String cargo;

	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date admissao;

}
