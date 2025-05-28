package com.plazas.usuarios.application.dto;

import com.plazas.usuarios.domain.model.Role;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequest {

    private String name;

    private String lastName;

    @Digits(integer = 15, fraction = 0, message = "El número de identidad debe ser númerico")
    private Long numberId;

    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "El número teléfonico no es valido")
    private String phoneNumber;

    private LocalDate birthDate;
    @Email
    private String email;

    private String password;

    @NotNull
    @Pattern(regexp = "^CUSTOMER$", message = "Debe ser el rol de cliente")
    private String role;

}
