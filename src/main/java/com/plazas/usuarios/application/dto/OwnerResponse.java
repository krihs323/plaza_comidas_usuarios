package com.plazas.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerResponse {

    private String name;
    private String lastName;
    private Long numberId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
}
