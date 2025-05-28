package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.mapper.OwnerRequestMapper;
import com.plazas.usuarios.application.mapper.RolResponseMapper;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
//@Transactional
public class OwnerHandler implements IOwnerHandler {

    //@Autowired
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
        //Implementa los casos de uso que se comunican con Dominio y Persistencia a traves de puertos
        User user = ownerRequestMapper.toOwner(ownerRequest);
        user.setRole(Role.OWNER);
        userServicePort.saveOwner(user);
    }

    //TODO REMOVER
    @Override
    public RolResponse getRolFromOwner(Long id) {
        User user = userServicePort.getRolFromOwner(id);
        return rolResponseMapper.toResponse(user);
    }

    @Override
    public void saveEmployee(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toOwner(ownerRequest);
        user.setRole(Role.EMPLOYEE);
        userServicePort.saveOwner(user);
    }

    @Override
    public RolResponse getRolFromEmail(String email) {
        User user = userServicePort.findByEmail(email);
        return rolResponseMapper.toResponse(user);
    }

}
