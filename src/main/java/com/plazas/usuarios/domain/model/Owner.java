package com.plazas.usuarios.domain.model;

import com.plazas.usuarios.infraestructure.exception.OwnerValidationException;
import com.plazas.usuarios.infraestructure.output.jpa.entity.RolType;

import java.time.LocalDate;
import java.time.Period;

public class Owner {

    private String name;
    private String lastName;
    private Long numberId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Integer rol;

    public Owner(String name, String lastName, Long numberId, String phoneNumber, LocalDate birthDate, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.numberId = numberId;
        this.phoneNumber = phoneNumber;
        this.birthDate = calcAge(birthDate);
        this.email = email;
        this.password = password;
        this.rol = RolType.OWNER.getValue();
    }


    private LocalDate calcAge(LocalDate birthDate) {
        int years;

        years = Period.between(birthDate, LocalDate.now()).getYears();
        if (years < 18) {
            throw new OwnerValidationException("El usuario no debe ser menor de edad");
        }
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
}
