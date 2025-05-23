package com.plazas.usuarios.infraestructure.exceptionhandler;

import com.plazas.usuarios.infraestructure.exception.OwnerAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.OwnerDoesNotExist;
import com.plazas.usuarios.infraestructure.exception.OwnerValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.Collections;
import java.util.Map;

//@ControllerAdvice(basePackages = {"com.plazas.usuarios.infraestructure"})
@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleOwnerAlreadyExistsException(
            MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handlerHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(OwnerValidationException.class)
    public ResponseEntity<Map<String, String>> ownerValidation(
            OwnerValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(OwnerAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handlerOwnerAlreadyexception(
            OwnerAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(OwnerDoesNotExist.class)
    public ResponseEntity<Map<String, String>> handlerOwnerDoesNotExist(
            OwnerDoesNotExist ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

}
