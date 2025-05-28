package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.RolResponse;

public interface IUserHandler {

    RolResponse getRolFromOwner(Long id);

}