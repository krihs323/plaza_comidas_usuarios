package com.plazas.usuarios.domain.api;

import com.plazas.usuarios.domain.model.User;

public interface IUserServicePort {

    void saveOwner(User user);

    User getRolFromOwner(Long id);

    User findByEmail(String email);

}
