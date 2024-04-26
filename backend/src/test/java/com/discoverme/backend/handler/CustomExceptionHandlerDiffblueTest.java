package com.discoverme.backend.handler;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.user.UserException;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import javax.security.auth.RefreshFailedException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ContextConfiguration(classes = {CustomExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class CustomExceptionHandlerDiffblueTest {
    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    /**
     * Method under test:
     */
    @Test
    void testAccountDisabledException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualAccountDisabledExceptionResult = customExceptionHandler
                .accountDisabledException(new DisabledException("foo"));

        // Assert
        HttpStatusCode expectedStatus = actualAccountDisabledExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualAccountDisabledExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test: {@link CustomExceptionHandler#badCredentialsException(BadCredentialsException)}
     */
    @Test
    void testBadCredentialsException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualBadCredentialsExceptionResult = customExceptionHandler
                .badCredentialsException(new BadCredentialsException("foo"));

        // Assert
        HttpStatusCode expectedStatus = actualBadCredentialsExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualBadCredentialsExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     */
    @Test
    void testAccessDeniedException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualAccessDeniedExceptionResult = customExceptionHandler.accessDeniedException(new AccessDeniedException("foo"));

        // Assert
        HttpStatusCode expectedStatus = actualAccessDeniedExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualAccessDeniedExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     */
    @Test
    void testLockedException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualLockedExceptionResult = customExceptionHandler.lockedException(new LockedException("foo"));

        // Assert
        HttpStatusCode expectedStatus = HttpStatus.LOCKED;
        assertSame(expectedStatus, actualLockedExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#tokenExpiredException(TokenExpiredException)}
     */
    @Test
    void testTokenExpiredException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualTokenExpiredExceptionResult = customExceptionHandler
                .tokenExpiredException(new TokenExpiredException("An error occurred",
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        HttpStatusCode expectedStatus = actualTokenExpiredExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualTokenExpiredExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#tokenExpiredException(TokenExpiredException)}
     */
    @Test
    void testTokenExpiredException2() {
        // Arrange
        TokenExpiredException ex = mock(TokenExpiredException.class);
        when(ex.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<ErrorResponse> actualTokenExpiredExceptionResult = customExceptionHandler.tokenExpiredException(ex);

        // Assert
        verify(ex).getMessage();
        HttpStatusCode expectedStatus = actualTokenExpiredExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualTokenExpiredExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#authenticationException(AuthenticationException)}
     */
    @Test
    void testAuthenticationException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualAuthenticationExceptionResult = customExceptionHandler
                .authenticationException(new AccountExpiredException("Msg"));

        // Assert
        HttpStatusCode expectedStatus = actualAuthenticationExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualAuthenticationExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#userException(UserException)}
     */
    @Test
    void testUserException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualUserExceptionResult = customExceptionHandler
                .userException(new UserException("An error occurred"));

        // Assert
        HttpStatusCode expectedStatus = actualUserExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualUserExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#userException(UserException)}
     */
    @Test
    void testUserException2() {
        // Arrange
        UserException exception = mock(UserException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<ErrorResponse> actualUserExceptionResult = customExceptionHandler.userException(exception);

        // Assert
        verify(exception).getMessage();
        HttpStatusCode expectedStatus = actualUserExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualUserExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#executionException(ExecutionException)}
     */
    @Test
    void testExecutionException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualExecutionExceptionResult = customExceptionHandler
                .executionException(new ExecutionException("foo", new Throwable()));

        // Assert
        HttpStatusCode expectedStatus = actualExecutionExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualExecutionExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#methodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    void testMethodArgumentNotValidException() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getMessage()).thenReturn("An error occurred");

        // Act
        ResponseEntity<ErrorResponse> actualMethodArgumentNotValidExceptionResult = customExceptionHandler
                .methodArgumentNotValidException(exception);

        // Assert
        verify(exception).getMessage();
        HttpStatusCode expectedStatus = actualMethodArgumentNotValidExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualMethodArgumentNotValidExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#constraintException(ConstraintViolationException)}
     */
    @Test
    void testConstraintException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualConstraintExceptionResult = customExceptionHandler
                .constraintException(new ConstraintViolationException(new HashSet<>()));

        // Assert
        HttpStatusCode expectedStatus = actualConstraintExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualConstraintExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#constraintException(ConstraintViolationException)}
     */
    @Test
    void testConstraintException2() {
        // Arrange
        ConstraintViolation<Object> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getPropertyPath()).thenReturn(mock(Path.class));
        when(constraintViolation.getMessage()).thenReturn("Not all who wander are lost");

        HashSet<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        constraintViolations.add(constraintViolation);

        // Act
        ResponseEntity<ErrorResponse> actualConstraintExceptionResult = customExceptionHandler
                .constraintException(new ConstraintViolationException(constraintViolations));

        // Assert
        verify(constraintViolation).getMessage();
        verify(constraintViolation).getPropertyPath();
        HttpStatusCode expectedStatus = actualConstraintExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualConstraintExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#usernameNotFoundException(UsernameNotFoundException)}
     */
    @Test
    void testUsernameNotFoundException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualUsernameNotFoundExceptionResult = customExceptionHandler
                .usernameNotFoundException(new UsernameNotFoundException("Msg"));

        // Assert
        HttpStatusCode expectedStatus = actualUsernameNotFoundExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualUsernameNotFoundExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#restClientException(RestClientException)}
     */
    @Test
    void testRestClientException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualRestClientExceptionResult = customExceptionHandler
                .restClientException(new RestClientException("Msg"));

        // Assert
        HttpStatusCode expectedStatus = actualRestClientExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualRestClientExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#restClientException(RestClientException)}
     */
    @Test
    void testRestClientException2() {
        // Arrange
        HttpClientErrorException exception = mock(HttpClientErrorException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<ErrorResponse> actualRestClientExceptionResult = customExceptionHandler
                .restClientException(exception);

        // Assert
        verify(exception).getMessage();
        HttpStatusCode expectedStatus = actualRestClientExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualRestClientExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#noHandlerFoundException(NoHandlerFoundException)}
     */
    @Test
    void testNoHandlerFoundException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualNoHandlerFoundExceptionResult = customExceptionHandler.noHandlerFoundException(
                new NoHandlerFoundException("https://example.org/example", "https://example.org/example", new HttpHeaders()));

        // Assert
        HttpStatusCode expectedStatus = actualNoHandlerFoundExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualNoHandlerFoundExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#noHandlerFoundException(NoHandlerFoundException)}
     */
    @Test
    void testNoHandlerFoundException2() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualNoHandlerFoundExceptionResult = customExceptionHandler
                .noHandlerFoundException(mock(NoHandlerFoundException.class));

        // Assert
        HttpStatusCode expectedStatus = actualNoHandlerFoundExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualNoHandlerFoundExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#methodNotSupportedException(HttpRequestMethodNotSupportedException)}
     */
    @Test
    void testMethodNotSupportedException() {
        // Arrange
        HashSet<HttpMethod> httpMethodSet = new HashSet<>();
        httpMethodSet.add(HttpMethod.valueOf("https://example.org/example"));
        HttpRequestMethodNotSupportedException exception = mock(HttpRequestMethodNotSupportedException.class);
        when(exception.getSupportedHttpMethods()).thenReturn(httpMethodSet);

        // Act
        ResponseEntity<ErrorResponse> actualMethodNotSupportedExceptionResult = customExceptionHandler
                .methodNotSupportedException(exception);

        // Assert
        verify(exception).getSupportedHttpMethods();
        HttpStatusCode expectedStatus = actualMethodNotSupportedExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualMethodNotSupportedExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#notFoundException(NoResultException)}
     */
    @Test
    void testNotFoundException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualNotFoundExceptionResult = customExceptionHandler
                .notFoundException(new NoResultException("An error occurred"));

        // Assert
        HttpStatusCode expectedStatus = actualNotFoundExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualNotFoundExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#notFoundException(NoResultException)}
     */
    @Test
    void testNotFoundException2() {
        // Arrange
        NoResultException exception = mock(NoResultException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<ErrorResponse> actualNotFoundExceptionResult = customExceptionHandler.notFoundException(exception);

        // Assert
        verify(exception).getMessage();
        HttpStatusCode expectedStatus = actualNotFoundExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualNotFoundExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#projectException(ProjectException)}
     */
    @Test
    void testProjectException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualProjectExceptionResult = customExceptionHandler
                .projectException(new ProjectException("An error occurred"));

        // Assert
        HttpStatusCode expectedStatus = actualProjectExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualProjectExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#projectException(ProjectException)}
     */
    @Test
    void testProjectException2() {
        // Arrange
        ProjectException exception = mock(ProjectException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<ErrorResponse> actualProjectExceptionResult = customExceptionHandler.projectException(exception);

        // Assert
        verify(exception).getMessage();
        HttpStatusCode expectedStatus = actualProjectExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualProjectExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#illegalArgumentException(IllegalArgumentException)}
     */
    @Test
    void testIllegalArgumentException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualIllegalArgumentExceptionResult = customExceptionHandler
                .illegalArgumentException(new IllegalArgumentException("foo"));

        // Assert
        HttpStatusCode expectedStatus = actualIllegalArgumentExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualIllegalArgumentExceptionResult.getBody().getStatus());
    }

    /**
     * Method under test:
     * {@link CustomExceptionHandler#refreshFailedException(RefreshFailedException)}
     */
    @Test
    void testRefreshFailedException() {
        // Arrange and Act
        ResponseEntity<ErrorResponse> actualRefreshFailedExceptionResult = customExceptionHandler
                .refreshFailedException(new RefreshFailedException("foo"));

        // Assert
        HttpStatusCode expectedStatus = actualRefreshFailedExceptionResult.getStatusCode();
        assertSame(expectedStatus, actualRefreshFailedExceptionResult.getBody().getStatus());
    }
}
