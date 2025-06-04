package com.plazas.usuarios.domain.validations;

public enum ConstantValidation {

    PATTERN_NUMBER("\\d{1,15}"),
    PATTERN_TELEPHONE("^\\+?\\d{1,13}$"),
    PATTERN_EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
    PATTERN_CUSTOMER_ROL("\"^CUSTOMER$\"");

    private String value;

    ConstantValidation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
