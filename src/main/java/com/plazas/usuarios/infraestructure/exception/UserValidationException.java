package com.plazas.usuarios.infraestructure.exception;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super(message);
    }

}
