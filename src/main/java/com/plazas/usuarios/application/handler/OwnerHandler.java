package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.dto.UserUpdateRequest;
import com.plazas.usuarios.application.mapper.OwnerRequestMapper;
import com.plazas.usuarios.application.mapper.RolResponseMapper;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OwnerHandler implements IOwnerHandler {

    private final IUserServicePort userServicePort;
    private final RolResponseMapper rolResponseMapper;
    private final OwnerRequestMapper ownerRequestMapper;

    public OwnerHandler(IUserServicePort userServicePort, RolResponseMapper rolResponseMapper, OwnerRequestMapper ownerRequestMapper) {
        this.userServicePort = userServicePort;
        this.rolResponseMapper = rolResponseMapper;
        this.ownerRequestMapper = ownerRequestMapper;
    }

    @Override
    public void saveOwner(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toUser(ownerRequest);
        userServicePort.saveOwner(user);
    }

    @Override
    public RolResponse getRolFromUser(Long id) {
        User user = userServicePort.getRolFromUser(id);
        return rolResponseMapper.toResponse(user);
    }

    @Override
    public void saveEmployee(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toUser(ownerRequest);
        userServicePort.saveEmployee(user);
    }

    @Override
    public RolResponse getRolFromEmail(String email) {
        User user = userServicePort.findByEmail(email);
        return rolResponseMapper.toResponse(user);
    }

    @Override
    public void updateOwnerRestaurant(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = ownerRequestMapper.toUser(userUpdateRequest);
        userServicePort.updateUser(userId, user);
    }

}
