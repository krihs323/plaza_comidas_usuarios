package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;

public interface IOwnerHandler {

    void saveOwner(OwnerRequest ownerRequest);
}