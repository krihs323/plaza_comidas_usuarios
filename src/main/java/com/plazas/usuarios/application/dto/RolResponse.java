package com.plazas.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolResponse {

    private String rol;

    private Long idUser;

    private Long idRestaurantEmployee;

    private String phoneNumber;

    private String name;

    private String lastName;


}
