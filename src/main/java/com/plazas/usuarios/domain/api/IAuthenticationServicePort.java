package com.plazas.usuarios.domain.api;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.domain.model.User;

public interface IAuthenticationServicePort {

    String authenticate(AuthenticationRequest request);
}
