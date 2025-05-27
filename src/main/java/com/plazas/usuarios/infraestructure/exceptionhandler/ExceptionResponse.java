package com.plazas.usuarios.infraestructure.exceptionhandler;

public enum ExceptionResponse {
    USER_VALIATION("El usuario no debe ser menor de edad"),
    USER_VALIDATION_EXIST("El usuario ya fue creado con el correo: "),
    USER_VALIDATION_NOT_FOUND("El usuario que intenta buscar no existe")
   ;

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}