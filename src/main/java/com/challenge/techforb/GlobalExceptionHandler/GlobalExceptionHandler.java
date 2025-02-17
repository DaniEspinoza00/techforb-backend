package com.challenge.techforb.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDTO> handleCustomException(CustomException customException) {
        ExceptionDTO errorResponse = ExceptionDTO.builder()
                .message(customException.getMessage())
                .statusCode(customException.getStatusCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(customException.getStatusCode()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDTO> handleBadCredentialsException(BadCredentialsException exception) {
        ExceptionDTO errorResponse = ExceptionDTO.builder()
                .message("Credenciales incorrectas: usuario o contraseña inválidos" + exception.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        ExceptionDTO errorResponse = ExceptionDTO.builder()
                .message("Ocurrió un error inesperado: " + exception.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
