package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.RolResponse;

public interface IOwnerHandler {

    void saveOwner(OwnerRequest ownerRequest);

    RolResponse getRolFromOwner(Long id);

    void saveEmployee(OwnerRequest ownerRequest);

    RolResponse getRolFromEmail(String email);
}