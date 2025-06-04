package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.Optional;


public class AuthenticationUserCase implements IAuthenticationServicePort {


    private final AuthenticationManager authenticationManager;
    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public AuthenticationUserCase(AuthenticationManager authenticationManager, IAuthenticationPersistencePort authenticationPersistencePort, IUserPersistencePort userPersistencePort) {
        this.authenticationManager = authenticationManager;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public String authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Optional<User> userFoundByEmail = userPersistencePort.findByEmail(request.getEmail());

        return authenticationPersistencePort.authenticate(userFoundByEmail.orElseThrow());
    }
}
