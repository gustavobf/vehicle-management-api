package br.com.batista.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resource, String value, String field) {
		super(String.format("%s with %s %s was not found.", resource, field, value));
	}

}
