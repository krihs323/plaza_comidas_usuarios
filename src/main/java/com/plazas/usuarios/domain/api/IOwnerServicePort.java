package com.plazas.usuarios.domain.api;

import com.plazas.usuarios.domain.model.Owner;

public interface IOwnerServicePort {

    void saveOwner(Owner owner);

    Owner getRolFromOwner(Long id);
}
