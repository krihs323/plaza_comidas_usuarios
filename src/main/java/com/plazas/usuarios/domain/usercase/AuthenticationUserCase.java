package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthenticationUserCase implements IAuthenticationServicePort {


    private final AuthenticationManager authenticationManager;
    private final IUserServicePort ownerServicePort;
    private final IAuthenticationPersistencePort authenticationPersistencePort;

    @Override
    public String authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = ownerServicePort.findByEmail(request.getEmail());

        return authenticationPersistencePort.authenticate(user);
    }
}
