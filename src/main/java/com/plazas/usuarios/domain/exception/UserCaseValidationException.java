package com.plazas.usuarios.domain.exception;

public class UserCaseValidationException extends RuntimeException {

    public UserCaseValidationException(String message) {
        super(message);
    }

}
