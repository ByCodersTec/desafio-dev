package br.com.bycoders.error.handler;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CustomException(String msg) {
		super(msg);
	}
}
