package com.plazas.usuarios.infraestructure.security.adapter;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.security.JwtService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationJpaAdapter implements IAuthenticationPersistencePort {

    private final JwtService jwtService;
    private final OwnerEntityMapper ownerEntityMapper;

    @Override
    public String authenticate(User user) {
        return jwtService.generateToken(ownerEntityMapper.toEntity(user));
    }
}
