package edu.fema.TrabalhoTopicosSpring.model.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import edu.fema.TrabalhoTopicosSpring.model.enums.GeneroEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NovoFuncionarioDTO {

	@NotEmpty
	private String nome;

	@NotEmpty
	private String cpf;

	@NotEmpty
	private String genero;

	@NotEmpty
	private String nomeDeUsuario;

	@NotEmpty
	private String senha;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date admissao;

}
