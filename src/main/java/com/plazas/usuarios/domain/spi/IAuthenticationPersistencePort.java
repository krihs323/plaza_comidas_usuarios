package com.plazas.usuarios.domain.spi;

import com.plazas.usuarios.domain.model.User;

public interface IAuthenticationPersistencePort {

    String authenticate(User user);
}
