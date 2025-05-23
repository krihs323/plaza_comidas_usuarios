package com.plazas.usuarios.domain.spi;

import com.plazas.usuarios.domain.model.Owner;

public interface IOwnerPersistencePort {

    void saveOwner(Owner owner);

    Owner getRolFromOwner(Long id);
}
