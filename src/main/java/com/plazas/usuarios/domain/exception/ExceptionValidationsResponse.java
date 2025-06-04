package com.plazas.usuarios.domain.exception;

public enum ExceptionValidationsResponse {
    USER_VALIATION_NAME("El nombre no debe ser nulo"),
    USER_VALIDATION_LAST_NAME("El apellido no debe ser nulo"),
    USER_VALIDATION_NUMBER_ID("El documento debe ser númerico"),
    USER_VALIDATION_TELEPHONE("El teléfono debe ser valido"),
    USER_VALIDATION_EMAIL("El email es incorrecto"),
    USER_VALIDATION_ROL("Debe ser el rol de cliente"),
    USER_VALIATION_AGE("El usuario no debe ser menor de edad"),;

    private String message;

    ExceptionValidationsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}