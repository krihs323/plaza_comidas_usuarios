package com.plazas.usuarios.domain.spi;

import com.plazas.usuarios.domain.model.User;

public interface IUserPersistencePort {

    void saveOwner(User user);

    User getRolFromOwner(Long id);

    User findByEmail(String email);
}
