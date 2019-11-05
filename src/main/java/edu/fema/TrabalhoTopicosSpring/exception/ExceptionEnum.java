package edu.fema.TrabalhoTopicosSpring.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

	USUARIO_JA_CADASTRADO("Já existe um usuário com este nome de usuário!", "username inválido",
			HttpStatus.BAD_REQUEST),
	USUARIO_INEXISTENTE("Não é possível registrar ponto para esse usuário", "codigo inválido", HttpStatus.BAD_REQUEST),

	USUARIO_NULL("Usuário nulo", "usuário inválido", HttpStatus.NOT_FOUND);

	private String mensagemUsuario;
	private String mensagemDev;
	private HttpStatus status;

}
