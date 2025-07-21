package br.com.batista.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String resource, String field, String value) {
		super(String.format("%s with %s '%s' already exists.", resource, field, value));
	}
}
