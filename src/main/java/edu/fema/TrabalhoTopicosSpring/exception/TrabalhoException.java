package edu.fema.TrabalhoTopicosSpring.exception;

import lombok.Getter;

public class TrabalhoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	@Getter
	private ExceptionEnum exception;
	
	public TrabalhoException(ExceptionEnum exception) {
		super(exception.getMensagemDev());
		this.exception = exception;
	}
	
}
