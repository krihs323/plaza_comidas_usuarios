package com.plazas.usuarios.domain.model;

import com.plazas.usuarios.infraestructure.exception.UserValidationException;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;

import java.time.LocalDate;
import java.time.Period;

public class User {

    private String name;
    private String lastName;
    private Long numberId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;
    private Long id;
    private Long idRestaurantEmployee;

    public User() {
    }

    public User(String name, String lastName, Long numberId, String phoneNumber, LocalDate birthDate, String email, String password, Role role, Long id, Long idRestaurantEmployee) {
        this.name = name;
        this.lastName = lastName;
        this.numberId = numberId;
        this.phoneNumber = phoneNumber;
        this.birthDate = calcAge(birthDate);
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
        this.idRestaurantEmployee = idRestaurantEmployee;
    }

    private LocalDate calcAge(LocalDate birthDate) {
        int years;

        years = Period.between(birthDate, LocalDate.now()).getYears();
        if (years < 18) {
            throw new UserValidationException(ExceptionResponse.USER_VALIATION.getMessage());
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRestaurantEmployee() {
        return idRestaurantEmployee;
    }

    public void setIdRestaurantEmployee(Long idRestaurantEmployee) {
        this.idRestaurantEmployee = idRestaurantEmployee;
    }
}
