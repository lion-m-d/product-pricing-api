package com.ws.infrastructure.price.config;


import com.ws.domain.price.exception.NotFoundException;
import com.ws.infrastructure.price.rest.dto.ErrorResponseDTO;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for managing various exceptions in the application.
 * <p>Handles exceptions like {@link NotFoundException}, {@link IllegalArgumentException},
 * {@link MethodArgumentTypeMismatchException}, {@link MissingServletRequestParameterException},
 * and general {@link Exception} to provide appropriate HTTP responses.</p>
 * @see NotFoundException
 * @see IllegalArgumentException
 * @see MethodArgumentTypeMismatchException
 * @see MissingServletRequestParameterException
 * @see ErrorResponseDTO
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**Handles {@link NotFoundException} and returns a 404 response.
     * @param ex the thrown exception
     * @param request the current web request
     * @return a {@link ResponseEntity} with error details
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(NotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles {@link IllegalArgumentException} and {@link MethodArgumentTypeMismatchException}
     * and returns a 400 Bad Request response.
     * @param ex the thrown exception
     * @param request the current web request
     * @return a {@link ResponseEntity} with error details
     */
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleIllegalArgument(Exception ex, WebRequest request) {

        var badRequest = HttpStatus.BAD_REQUEST;
        log.error("exception - {} : {}",badRequest.value(), ex.getMessage());
        return buildErrorResponse(badRequest, badRequest.getReasonPhrase());
    }


    /**
     * Handles any {@link Exception} and returns a 500 Internal Server Error response.
     * @param ex the thrown exception
     * @param request the current web request
     * @return a {@link ResponseEntity} with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        log.error("exception : {}",ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    /**
     * Handles {@link MissingServletRequestParameterException} and returns a 400 Bad Request
     * response for missing required query parameters.
     * @param ex the thrown exception
     * @return a {@link ResponseEntity} with error details
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                String.format("The required query parameter '%s' is missing.", ex.getParameterName()));
    }

    /**
     * Builds the error response for the client.
     *
     * @param status the HTTP status code
     * @param message the error message
     * @return a {@link ResponseEntity} with error details
     */
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {

        log.error("exception - {} : {}", status, message);
        var body = new ErrorResponseDTO();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setMessage(message);
        return new ResponseEntity<>(body, status);
    }
}
