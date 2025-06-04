package com.plazas.usuarios.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerRequest {

        private String name;

        private String lastName;

        @Digits(integer = 15, fraction = 0, message = "El número de identidad debe ser númerico")
        private Long numberId;

        private String phoneNumber;

        private LocalDate birthDate;

        private String email;

        private String password;

}
