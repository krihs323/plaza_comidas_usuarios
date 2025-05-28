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
//@RequiredArgsConstructor
//@Transactional
public class OwnerHandler implements IOwnerHandler {

    //@Autowired
    private final IOwnerServicePort ownerServicePort;
    private final RolResponseMapper rolResponseMapper;
    private final OwnerRequestMapper ownerRequestMapper;

    public OwnerHandler(IOwnerServicePort ownerServicePort, RolResponseMapper rolResponseMapper, OwnerRequestMapper ownerRequestMapper) {
        this.ownerServicePort = ownerServicePort;
        this.rolResponseMapper = rolResponseMapper;
        this.ownerRequestMapper = ownerRequestMapper;
    }

    @Override
    public void saveOwner(OwnerRequest ownerRequest) {
        //Implementa los casos de uso que se comunican con Dominio y Persistencia a traves de puertos
        User user = ownerRequestMapper.toOwner(ownerRequest);
        user.setRole(Role.OWNER);
        ownerServicePort.saveOwner(user);
    }

    //TODO REMOVER
    @Override
    public RolResponse getRolFromOwner(Long id) {
        User user = ownerServicePort.getRolFromOwner(id);
        return rolResponseMapper.toResponse(user);
    }

    @Override
    public void saveEmployee(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toOwner(ownerRequest);
        user.setRole(Role.EMPLOYEE);
        ownerServicePort.saveOwner(user);
    }

    @Override
    public RolResponse getRolFromEmail(String email) {
        User user = ownerServicePort.findByEmail(email);
        return rolResponseMapper.toResponse(user);
    }

}
