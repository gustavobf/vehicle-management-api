package br.com.batista.dto.api.response;

import io.swagger.v3.oas.annotations.media.*;

public class ResponseDto {

    @Schema(description = "HTTP status code of the response", example = "200", required = true)
    private int statusCode;

    @Schema(description = "Response message", example = "Operation completed successfully", required = true)
    private String message;

    public ResponseDto () {
    }

    public ResponseDto (int statusCode, String message) {
        super();
        this.setStatusCode(statusCode);
        this.setMessage(message);
    }

    public int getStatusCode () {
        return statusCode;
    }

    public void setStatusCode (int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

}
