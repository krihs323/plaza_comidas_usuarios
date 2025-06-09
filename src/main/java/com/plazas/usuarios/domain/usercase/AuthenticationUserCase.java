package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.exception.AuthenticationException;
import com.plazas.usuarios.domain.exception.ExceptionResponse;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
//TODO Manejarlo en la infraestructura - Ajustado
import java.util.Optional;


public class AuthenticationUserCase implements IAuthenticationServicePort {

    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public AuthenticationUserCase(IAuthenticationPersistencePort authenticationPersistencePort, IUserPersistencePort userPersistencePort) {
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public String authenticate(AuthenticationRequest request) {

        authenticationPersistencePort.autenticate(request.getEmail(), request.getPassword());

        Optional<User> userFoundByEmail = userPersistencePort.findByEmail(request.getEmail());
        //TODO Arrojar excepcion especifica como un 401 usuario no encontrado - Ajustado
        if (userFoundByEmail.isEmpty()){
            throw new AuthenticationException(ExceptionResponse.AUTHENTICATION_VALIDATION.getMessage());
        }
        return authenticationPersistencePort.authenticate(userFoundByEmail.orElseThrow());

    }
}
