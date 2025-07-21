package br.com.batista.dto.api.response;

public class ResponseDto {

	private int statusCode;
	private String message;

	public ResponseDto() {
	}

	public ResponseDto(int statusCode, String message) {
		super();
		this.setStatusCode(statusCode);
		this.setMessage(message);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
