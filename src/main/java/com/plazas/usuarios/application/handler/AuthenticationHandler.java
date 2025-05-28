package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.application.dto.AuthenticationResponse;
import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.infraestructure.security.JwtService;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserServicePort ownerServicePort;
    private final IAuthenticationServicePort authenticationServicePort;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        String token = authenticationServicePort.authenticate(request);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
