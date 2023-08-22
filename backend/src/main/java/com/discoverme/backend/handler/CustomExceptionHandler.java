package com.discoverme.backend.handler;

import com.discoverme.backend.commons.ErrorResponse;
import com.discoverme.backend.user.registration.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UsernameNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    public final ResponseEntity<ErrorResponse> handleUserRegistrationException(RegistrationException ex) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<ErrorResponse> handleCommandNotSupportedException(IOException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    private <E extends RuntimeException> ResponseEntity<ErrorResponse> buildResponse(E exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
