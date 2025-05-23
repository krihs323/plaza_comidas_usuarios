package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.OwnerResponse;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.mapper.OwnerRequestMapper;
import com.plazas.usuarios.application.mapper.RolResponseMapper;
import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.Owner;
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
        Owner owner = ownerRequestMapper.toOwner(ownerRequest);
        ownerServicePort.saveOwner(owner);

    }

    @Override
    public RolResponse getRolFromOwner(Long id) {
        Owner owner = ownerServicePort.getRolFromOwner(id);
        return rolResponseMapper.toResponse(owner);
    }

}
