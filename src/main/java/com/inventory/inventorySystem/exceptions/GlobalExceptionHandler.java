package com.inventory.inventorySystem.exceptions;

import com.inventory.inventorySystem.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest request) {
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        var errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                errors,
                "The provided data does not meet the required format or constraints.",
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                exception.getMessage(),
                "The requested resource was not found in the database.",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                exception.getMessage(),
                "Invalid username or password",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationExceptionException(AuthenticationException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                exception.getMessage(),
                "Authentication error",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidTokenException(InvalidTokenException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                exception.getMessage(),
                "Invalid token",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Forbidden",
                "You do not have permission to perform this action.",
                "Authorization Error",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(StockExhaustedException.class)
    public ResponseEntity<ApiErrorResponse> handleStockInsufficientException(StockExhaustedException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage(),
                "The requested quantity exceeds the available stock.",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPaymentStateException(PaymentProcessingException exception, WebRequest request) {
        var errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Invalid Payment State",
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

