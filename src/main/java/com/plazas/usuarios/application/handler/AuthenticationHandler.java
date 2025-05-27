package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.application.dto.AuthenticationResponse;
import com.plazas.usuarios.domain.api.IAuthenticationServicePort;
import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.security.JwtService;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IOwnerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IOwnerServicePort ownerServicePort;
    private final IAuthenticationServicePort authenticationServicePort;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        String token = authenticationServicePort.authenticate(request);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
