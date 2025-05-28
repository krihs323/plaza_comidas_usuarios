package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.mapper.RolResponseMapper;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserHandler implements IUserHandler {

    private final IUserServicePort ownerServicePort;
    private final RolResponseMapper rolResponseMapper;

    public UserHandler(IUserServicePort ownerServicePort, RolResponseMapper rolResponseMapper) {
        this.ownerServicePort = ownerServicePort;
        this.rolResponseMapper = rolResponseMapper;
    }


    @Override
    public RolResponse getRolFromOwner(Long id) {
        User user = ownerServicePort.getRolFromOwner(id);
        return rolResponseMapper.toResponse(user);
    }


}
