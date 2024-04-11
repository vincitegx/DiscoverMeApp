package com.discoverme.backend.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.user.UserException;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.RefreshFailedException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleApiRequestException(Exception e) {
//        return ResponseEntity.badRequest().body(
//                ErrorResponse.builder()
//                        .timeStamp(LocalDateTime.now())
//                        .message(e.getMessage())
//                        .status(HttpStatus.BAD_REQUEST)
//                        .build());
//    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> accountDisabledException() {
        return createErrorResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException() {
        return createErrorResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException() {
        return createErrorResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponse> lockedException() {
        return createErrorResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> tokenExpiredException(TokenExpiredException ex) {
        return createErrorResponse(UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authenticationException(AuthenticationException ex) {
        return createErrorResponse(UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userException(UserException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorResponse> executionException(ExecutionException exception) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintException(ConstraintViolationException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> usernameNotFoundException(UsernameNotFoundException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> restClientException(RestClientException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFoundException(NoHandlerFoundException e) {
        return createErrorResponse(BAD_REQUEST, "There is no mapping for this URL");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createErrorResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NoResultException exception) {
        return createErrorResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> projectException(ProjectException exception) {
        return createErrorResponse(EXPECTATION_FAILED, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage());
    }

//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<ErrorResponse> iOException(IOException exception) {
//        return createErrorResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
//    }

    @ExceptionHandler(RefreshFailedException.class)
    public ResponseEntity<ErrorResponse> refreshFailedException(RefreshFailedException exception) {
        return createErrorResponse(UNAUTHORIZED, exception.getMessage());
    }


    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(),httpStatus,httpStatus.getReasonPhrase().toUpperCase(), message,null),
                httpStatus);
    }
}


