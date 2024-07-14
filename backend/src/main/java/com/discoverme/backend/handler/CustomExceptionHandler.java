package com.discoverme.backend.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.user.UserException;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
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
    public ResponseEntity<ErrorResponse> accountDisabledException(DisabledException exception) {
        return createErrorResponse(BAD_REQUEST, ACCOUNT_DISABLED, exception.getLocalizedMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException exception) {
        return createErrorResponse(BAD_REQUEST, INCORRECT_CREDENTIALS, exception.getLocalizedMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException exception) {
        return createErrorResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION, exception.getLocalizedMessage());
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponse> lockedException(LockedException exception) {
        return createErrorResponse(UNAUTHORIZED, ACCOUNT_LOCKED, exception.getLocalizedMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> jWTVerificationException(JWTVerificationException ex) {
        System.out.println("Token Expired Exception");
        return createErrorResponse(UNAUTHORIZED, ex.getMessage(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> tokenExpiredException(TokenExpiredException ex) {
        System.out.println("Token Expired Exception");
        return createErrorResponse(UNAUTHORIZED, ex.getMessage(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authenticationException(AuthenticationException ex) {
        return createErrorResponse(UNAUTHORIZED, ex.getMessage(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userException(UserException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorResponse> executionException(ExecutionException exception) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintException(ConstraintViolationException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> usernameNotFoundException(UsernameNotFoundException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> restClientException(RestClientException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFoundException(NoHandlerFoundException e) {
        return createErrorResponse(BAD_REQUEST, "There is no mapping for this URL", e.getLocalizedMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createErrorResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod), exception.getLocalizedMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NoResultException exception) {
        return createErrorResponse(NOT_FOUND, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> projectException(ProjectException exception) {
        return createErrorResponse(EXPECTATION_FAILED, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        return createErrorResponse(BAD_REQUEST, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> iOException(IOException exception) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, exception.getMessage(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(RefreshFailedException.class)
    public ResponseEntity<ErrorResponse> refreshFailedException(RefreshFailedException exception) {
        return createErrorResponse(FORBIDDEN, exception.getMessage(), exception.getLocalizedMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex.getMessage());
//    }
//
//
//    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus,String message, String developerMessage) {
//        return new ResponseEntity<>(
//                new ErrorResponse(LocalDateTime.now(),httpStatus,message, developerMessage,null),
//                httpStatus);
//    }

    @ExceptionHandler(ClientAbortException.class)
    public void handleClientAbortException(ClientAbortException ex) {
        logger.warn("Client aborted the connection", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Request URL: " + request.getRequestURL(), ex);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus, String message, String developerMessage) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(LocalDateTime.now(), httpStatus, message, developerMessage, null));
    }
}


