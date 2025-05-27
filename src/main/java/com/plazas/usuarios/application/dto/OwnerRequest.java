package com.plazas.usuarios.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plazas.usuarios.domain.model.Role;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerRequest {

        private String name;

        private String lastName;

        @Digits(integer = 15, fraction = 0, message = "El número de identidad debe ser númerico")
        private Long numberId;

        //@Size(min = 1, max = 13)
        @Pattern(regexp = "^\\+?\\d{1,13}$", message = "El número teléfonico no es valido")
        private String phoneNumber;

        private LocalDate birthDate;
        //Debe contener estructura valida del email
        @Email
        private String email;

        //Debe estar encriptada
        private String password;

}
