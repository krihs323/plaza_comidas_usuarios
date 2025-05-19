package com.plazas.usuarios.infraestructure.exception;

public class OwnerAlreadyExistException extends RuntimeException {

    public OwnerAlreadyExistException(String message) {
        super(message);
    }
}
