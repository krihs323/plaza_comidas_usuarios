package com.plazas.usuarios.infraestructure.exception;

public class OwnerDoesNotExist extends RuntimeException {
    public OwnerDoesNotExist(String message) {
        super(message);
    }
}
