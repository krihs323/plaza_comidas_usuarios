package com.plazas.usuarios.infraestructure.security.adapter;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.plazas.usuarios.infraestructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthenticationJpaAdapter implements IAuthenticationPersistencePort {

    private final JwtService jwtService;
    private final UserEntityMapper userEntityMapper;
    private final AuthenticationManager authenticationManager;


    @Override
    public String authenticate(User user) {
        return jwtService.generateToken(userEntityMapper.toEntity(user), user);
    }

    @Override
    public void autenticate(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

    }
}
