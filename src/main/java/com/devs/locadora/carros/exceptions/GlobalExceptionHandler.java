package com.devs.locadora.carros.exceptions;

import com.devs.locadora.carros.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound(ResourceNotFoundException e) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validationError(MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getFieldErrors()
                        .get(0).getDefaultMessage();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "Bad Request", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> businessError(BusinessException e) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "Bad Request", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

}
