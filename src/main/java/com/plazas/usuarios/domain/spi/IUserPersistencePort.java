package com.plazas.usuarios.domain.spi;

import com.plazas.usuarios.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveOwner(User user);

    User getRolFromOwner(Long id);

    Optional<User> findByEmail(String email);

    String encodePassword(String password);
}
