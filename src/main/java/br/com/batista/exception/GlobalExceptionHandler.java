package br.com.batista.exception;

import br.com.batista.dto.api.response.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import java.time.*;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, NoSuchElementException.class, UserAlreadyExistsException.class})
    public ResponseEntity<ErrorResponseDto> handleExceptions(RuntimeException ex, WebRequest request) {
        HttpStatus status;
        String message;

        switch (ex) {
            case ResourceNotFoundException rnf -> {
                status = HttpStatus.NOT_FOUND;
                message = rnf.getMessage();
            }
            case NoSuchElementException nsee -> {
                status = HttpStatus.NOT_FOUND;
                message = nsee.getMessage();
            }
            case UserAlreadyExistsException uaee -> {
                status = HttpStatus.CONFLICT;
                message = uaee.getMessage();
            }
            default -> {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                message = "Unexpected error occurred";
            }
        }

        ErrorResponseDto error = new ErrorResponseDto(
                request.getDescription(false),
                status,
                message,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions (MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
