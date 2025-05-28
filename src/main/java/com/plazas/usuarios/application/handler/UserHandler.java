package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.mapper.OwnerRequestMapper;
import com.plazas.usuarios.application.mapper.RolResponseMapper;
import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserHandler implements IUserHandler {

    private final IOwnerServicePort ownerServicePort;
    private final RolResponseMapper rolResponseMapper;

    public UserHandler(IOwnerServicePort ownerServicePort, RolResponseMapper rolResponseMapper) {
        this.ownerServicePort = ownerServicePort;
        this.rolResponseMapper = rolResponseMapper;
    }


    @Override
    public RolResponse getRolFromOwner(Long id) {
        User user = ownerServicePort.getRolFromOwner(id);
        return rolResponseMapper.toResponse(user);
    }


}
