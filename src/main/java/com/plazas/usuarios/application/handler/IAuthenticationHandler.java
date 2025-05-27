package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.application.dto.AuthenticationResponse;

public interface IAuthenticationHandler {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
