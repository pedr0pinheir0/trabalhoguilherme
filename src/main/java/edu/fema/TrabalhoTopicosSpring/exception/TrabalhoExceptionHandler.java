package edu.fema.TrabalhoTopicosSpring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class TrabalhoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ TrabalhoException.class })
	public ResponseEntity<?> handleDevCaseException(TrabalhoException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				new RetornoException(ex.getException().getMensagemDev(), ex.getException().getMensagemUsuario()),
				new HttpHeaders(), ex.getException().getStatus(), request);
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	class RetornoException {

		private String mensagemDev;
		private String mensagemUsuario;
	}

}